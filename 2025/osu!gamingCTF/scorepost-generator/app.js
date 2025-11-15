const { ScoreDecoder, BeatmapDecoder } = require("osu-parsers");
const { Rank } = require("osu-classes");
const fsp = require("fs/promises");
const express = require("express");
const multer = require("multer");
const crypto = require("crypto");
const JSZip = require("jszip");
const path = require("path");
const os = require("os");

const { generateScreenshot } = require("./scorepost");

const PORT = process.env.PORT || 1337;

const storage = multer.memoryStorage({
    // sorry, max 16mb :(
    limits: {
        fileSize: 16 * 1024 * 1024
    }
});
const upload = multer({ storage: storage });
const app = express();

app.use(express.static("public"));

const md5 = data => crypto.createHash('md5').update(data).digest("hex");

const withTempDir = async (fn) => {
    const dir = await fsp.mkdtemp(await fsp.realpath(os.tmpdir()) + path.sep);
    try {
        return await fn(dir);
    } finally {
        await fsp.rm(dir, { recursive: true });
    }
};

app.post("/api/submit", upload.fields([
    { name: "osz", maxCount: 1 },
    { name: "osr", maxCount: 1 }
]), async (req, res) => {
    const osz = req.files?.osz?.[0];
    const osr = req.files?.osr?.[0];
    
    if (!osr || !osz) {
        return res.redirect("/?error=" + encodeURIComponent("Error: missing osz or osr file"));
    }

    const score = await (new ScoreDecoder()).decodeFromBuffer(osr.buffer, true);
    if (score.info._rulesetId !== 0) {
        return res.redirect("/?error=" + encodeURIComponent("Error: sorry, only standard is supported :("));
    }

    const beatmapHashMD5 = score.info.beatmapHashMD5;
    
    const zip = new JSZip();
    const oszFile = await zip.loadAsync(osz.buffer);
    const zipMaps = Object.entries(oszFile.files).filter(([name, file]) => name.endsWith(".osu") && file._data.uncompressedSize < 1 * 1024 * 1024);
    
    if (zipMaps.length === 0) {
        return res.redirect("/?error=" + encodeURIComponent("Error: no maps found in osz"));
    }

    const osuMaps = await Promise.all(zipMaps.map(([name]) => zip.file(name).async("uint8array")));
    const map = osuMaps.find(map => md5(map) === beatmapHashMD5);
    if (!map) {
        return res.redirect("/?error=" + encodeURIComponent("Error: could not find the correct beatmap for that replay"));
    }

    const beatmap = await (new BeatmapDecoder()).decodeFromBuffer(map);
    const bgFile = await zip.file(beatmap.events.backgroundPath);
    if (!bgFile || bgFile._data.uncompressedSize > 4 * 1024 * 1024) {
        return res.redirect("/?error=" + encodeURIComponent("Error: missing background image"));
    }
    const beatmapBG = await bgFile.async("uint8array");

    score.info.passed = true; // uhh idk
    const rank = Rank.calculate(score.info);

    const scoreInfo = {
        beatmapset_artist: beatmap.metadata.artist,
        beatmapset_title: beatmap.metadata.title,
        beatmap_version: beatmap.metadata.version,
        beatmapset_creator: beatmap.metadata.creator,
        username: score.info.username,
        created_at: new Date().toLocaleDateString('en-GB', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        }).replace(/\//g, '.'),
        mods: score.info?.mods?.acronyms?.join('') || 'NM',
        rank,
        score: score.info.totalScore,
        accuracy: score.info.accuracy,
        max_combo: score.info.maxCombo,
        count_300: score.info.count300,
        count_100: score.info.count100,
        count_50: score.info.count50,
        count_miss: score.info.countMiss,
        count_geki: score.info.countGeki,
        count_katu: score.info.countKatu
    };

    res.setHeader("Content-Type", "image/png");
    
    await withTempDir(async (dir) => {
        const bgPath = path.join(dir, "bg.png");
        await fsp.writeFile(bgPath, beatmapBG);
        const outputPath = await generateScreenshot(dir, bgPath, scoreInfo);
        res.sendFile(outputPath);
    });
});

app.listen(PORT, (err) => console.log(err ?? `web/scorepost-generator listening on port ${PORT}`));