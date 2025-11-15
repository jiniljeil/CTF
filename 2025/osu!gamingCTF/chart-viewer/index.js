const express = require('express');
const multer = require('multer');
const fs = require('fs');
const StreamZip = require('node-stream-zip');
const path = require('path');
const { spawnSync } = require('child_process');

const MAX_UPLOAD_BYTES = 2 * 1024 * 1024; // 2 MB

const UPLOAD_DIR = '/tmp/uploads';
if (!fs.existsSync(UPLOAD_DIR)) fs.mkdirSync(UPLOAD_DIR, { recursive: true });

const storage = multer.diskStorage({
  destination: (req, file, cb) => cb(null, UPLOAD_DIR),
  filename: (req, file, cb) => cb(null, req.body.name || file.originalname)
});

const fileFilter = (req, file, cb) => {
  const name = req.body.name || file.originalname;
  if (name.includes('..') || name.includes('/') || name.includes('\\')) {
    return cb(null, false);
  }
  cb(null, true);
};
const upload = multer({ storage, fileFilter });


const app = express();
app.use(express.static(path.join(__dirname, 'public')));

app.post('/upload', upload.single('file'), (req, res) => {
  if (!req.file) return res.status(400).send('no file uploaded, check filename');
  if (req.file.filename.includes('..') || req.file.filename.includes('/')) {
    return res.status(400).send('invalid filename');
  }
  res.send(`${req.file.filename}`);
});


app.get('/process', async (req, res) => {
  const name = req.query.name;
  const entryName = req.query.file;
  const startTime = Date.now();
  if (!name || !entryName) return res.status(400).send('missing params');
  if (name.includes('..') || name.includes('/') || name.length > 1) {
    // I made some errors here - but still should be solvable :clueless:
    return res.status(400).send('bad zip name');
  }

  const zipPath = path.join(UPLOAD_DIR, `${name}`);
  try {
    const zip = new StreamZip.async({ file: zipPath });
    const entries = await zip.entries();
    for (const [ename, entry] of Object.entries(entries)) {
      const archiveEntryName = ename;

      const unixStyle = String(archiveEntryName).replace(/\\/g, '/');
      if (unixStyle.includes('\0') || /[\x00-\x1f]/.test(unixStyle)) {
        await zip.close();
        console.log('Bad zip entry (null/control bytes):', archiveEntryName);
        return res.status(400).send('bad zip entry (invalid chars)');
      }
      const normalized = path.posix.normalize(unixStyle);

      if (
        normalized === '' ||
        normalized.startsWith('/') ||
        /^[a-zA-Z]:\//.test(unixStyle) ||
        normalized.split('/').some(seg => seg === '..')
      ) {
        await zip.close();
        console.log('Found path traversal entry:', archiveEntryName);
        return res.status(400).send('bad zip entry (path traversal)');
      }

      const attr = entry && entry.attr ? entry.attr : 0;
      const looksLikeSymlink = (((attr >> 16) & 0xFFFF) & 0o170000) === 0o120000;
      if (looksLikeSymlink) {
        await zip.close();
        console.log('Found symlink via external attributes:', archiveEntryName);
        return res.status(400).send('symlinks not allowed (detected)');
      }

    }
    await zip.close();
  } catch (err) {
    console.log(err);
    return res.status(500).send('check error');
  }
  try {
    if (entryName.includes('..') || entryName.includes('/')) {
      return res.status(400).send('bad entry name');
    }
    const extractDir = path.join(UPLOAD_DIR, `${name}_extracted`);

    if (!fs.existsSync(extractDir)) fs.mkdirSync(extractDir);

    await new Promise(resolve => setTimeout(() => { fs.copyFileSync(zipPath, path.join(extractDir, path.basename(zipPath))); resolve(); }, 1000));
    const unzipResult = spawnSync('unzip', ['-o', path.join(extractDir, path.basename(zipPath))], { cwd: extractDir, timeout: 10000 });
    if (unzipResult.status !== 0) {
      console.log(`Unzip error: ${unzipResult.stderr.toString()}, ${unzipResult.status}`);
      return res.status(500).send('unzip error');
    }

    const entryPath = path.join(extractDir, path.basename(`${entryName}`));
    const contents = fs.readFileSync(entryPath, 'utf8');
    console.log(`Reading entry from path: ${entryPath}`);

    if (!fs.existsSync(entryPath)) {
      return res.status(404).send('entry not found (second check)');
    }
    fs.readFile(entryPath, 'utf8', (err, data) => {
      if (err) return console.error(err);
    });

    if (!entryPath.endsWith('.jpg') && entryName.length > 1) {
      return res.status(400).send('only .jpg files allowed');
    }

    if (!contents) return res.status(404).send('entry not found');
    return res.type('text/plain').send(contents);
  } catch (err) {
    console.log(err);
    return res.status(500).send('read error');
  }
});


app.post('/render', (req, res) => {
  const sharp = require('sharp');

  const contentLength = parseInt(req.headers['content-length'] || '0', 10);
  if (contentLength && contentLength > MAX_UPLOAD_BYTES) return res.status(413).send('file too large');

  let bytes = 0;
  let aborted = false;
  req.on('data', c => {
    bytes += c.length;
    if (bytes > MAX_UPLOAD_BYTES && !aborted) {
      aborted = true;
      req.destroy();
      try { res.status(413).send('file too large'); } catch (e) { }
    }
  });

  const transformer = sharp({ failOnError: true })
    .ensureAlpha()
    .removeAlpha()
    .resize(16, 1, { fit: 'fill' });

  req.pipe(transformer);

  transformer
    .raw()
    .toBuffer({ resolveWithObject: true })
    .then(({ data, info }) => {
      if (aborted) return;
      if (!info || info.channels < 3) return res.status(400).send('unsupported image');

      const channels = info.channels;
      const sampled = [];
      for (let x = 0; x < info.width; x++) {
        const idx = x * channels;
        const r = data[idx];
        const g = data[idx + 1];
        const b = data[idx + 2];
        sampled.push(rgbToHex(r, g, b));
      }
      return res.json({
        controlColors: sampled,
      });
    })
    .catch(err => {
      if (!res.headersSent) {
        console.error('render error', err && err.message ? err.message : err);
        res.status(400).send('image processing error');
      }
      try { transformer.destroy(); } catch (e) { }
      try { req.destroy(); } catch (e) { }
    });

  req.on('close', () => {
    try { transformer.destroy(); } catch (e) { }
  });
});

function rgbToHex(r, g, b) {
  return '#' + [r, g, b].map(v => (v & 0xff).toString(16).padStart(2, '0')).join('');
}


app.listen(3000, () => console.log('listening :3000'));
