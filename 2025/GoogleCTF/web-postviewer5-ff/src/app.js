const express = require("express");
const { visit } = require('./bot.js');

const PORT = process.env.PORT || 1338;

const sec_headers = (req, res, next) => {
  res.set("Cache-control", "public, max-age=300");
  res.setHeader("X-Content-Type-Options", "nosniff");
  res.setHeader("X-Frame-Options", "DENY");
  res.setHeader("Referrer-Policy", "no-referrer");
  return next();
};

const app = express();

app.use(sec_headers);


app.use(express.urlencoded({ extended: false }));

app.set("view engine", "ejs");
app.use("/static/", express.static("static"));

console.log(process.env);

app.get("/", (req, res) => {
  res.render("index");
});

app.get("/bot", (req, res) => {
  res.render("bot");
});

app.post("/bot", async (req, res) => {
  const { url } = req.body;

  res.writeHead(200, {
    'Content-Type': "text/event-stream",
    'Cache-Control': "no-cache",
    'Connection': "keep-alive"
  });

  if (typeof url !== 'string' || !/^https?:\/\//.test(url)) {
    return res.end('nice try');
  }

  function log(msg) {
    res.write(msg + '\n');
  }

  res.write('URL was sent to admin\n');
  try {
    await visit(url, log);
  } catch (e) {
    res.write("error.");
  } finally {
    res.end('done.');
  }
});

app.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
  console.log("Press Ctrl+C to quit.");
});

module.exports = app;
