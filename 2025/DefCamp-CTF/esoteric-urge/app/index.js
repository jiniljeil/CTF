import express from 'express';
import path from 'path';
import NodeCache from 'node-cache';
import session from 'express-session';
import crypto from 'crypto';
import { fileURLToPath } from 'url';
import { dirname, join } from 'path';

import * as utils from './utils.js';
import { astralTravel } from './traveller.js';
import * as middleware from './middleware.js';
import User from './models/user.js';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const port = 3000;

await utils.connectDB();

app.use(session({
  secret: process.env.SECRET ||'SECRET',
  resave: false,
  saveUninitialized: true,
  cookie: { secure: false, httpOnly: true }
}));

const cache = new NodeCache({ stdTTL: 60 }); 
// Help for large files
app.use('/public', middleware.cacheFiles(cache), express.static('public'));
// Basic functionalities
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use((req, res, next) => {
  req.url = path.normalize(decodeURIComponent(req.path));
  next();
});

// Set views
app.set('view engine', 'pug');
app.set('views', path.join(__dirname, 'views'));

utils.beginProjection();

app.get('/', (req, res) => {
  res.render("home");
});

app.get('/awaken', middleware.csrfPrepare, (req, res) => {
  res.render("awaken");
});

app.post('/awaken', middleware.csrfProtect, async (req, res) => {
  try {
    const username = req.body.username;
    const found = await User.findOne({ username });
    if (found) {
      res.status(200).render("message", { text: `The UNiverse is waiting for you, ${username}` });
      return;
    }
    const user = req.session.user;
    const password = crypto.randomBytes(20).toString('hex').slice(0, 20);
    let role = null;
    if (user && user.role === 'guide') {
      role = req.body.role;
    }
    await User.create({ username, password: utils.hash(password), role: role || 'adept' });
    res.render("awaken", { username, password });
  } catch {
    res.status(500).send('It\'s only a bad dream');
  }
});

app.get('/transcend', middleware.requireLogin, middleware.csrfPrepare, (req, res) => {
  res.render("transcend");
});

app.post('/transcend', middleware.requireLogin, middleware.csrfProtect, (req, res) => {
  const url = req.body.url;
  if (!url) {
    res.render("message", { text: 'The URL is yet to be.' });
    return;
  }
  if (!url.startsWith('http://') && !url.startsWith('https://')) {
    res.render("message", { text: 'URL does not have the right frequency.' });
    return;
  }
  astralTravel(url);
  res.render("message", { text: 'Light will be shining over you.' });
});

app.delete('/reach_nirvana', middleware.requireEsotericKnowledge, async (req, res) => {
  res.status(200).sendFile(path.join(__dirname, 'nirvana.txt'));
  await utils.sleep(3);
  process.exit();
});

app.get('/login', middleware.csrfPrepare, (req, res) => {
  res.render("login");
});

app.post('/login', middleware.csrfProtect, async (req, res) => {
  try {
    const { username, password } = req.body;
    const user = await User.findOne({ username, password: utils.hash(password) });
    if (user) {
      res.clearCookie('session');
      req.session.user = {
        id: user.id,
        username: user.username,
        role: user.role
      };
      res.redirect('/transcend');
    } else {
      res.status(401).send({ error: 'Darkness is more revealing than your words.' });
    }
  } catch {
    res.status(500).send('SHE is coming, yet thirstier than before');
  }
});

app.get('/logout', (req, res) => {
  res.clearCookie('session');
  req.session.user = undefined;
  res.redirect('/login');
});

// Init
app.listen(port, () => {
  console.log(`App listening on port ${port}`);
});
