const { openDelimiter } = require("ejs");
const express = require("express");
const fs = require("fs");
const ejs = require("ejs")

const app = express();


const template = `<!DOCTYPE html>
<html>
<head>
    <title>Example</title>
</head>
<body>
    Content:
    PAGE
</body>
</html>`

app.get("/", (req, res) => {
    const page = req.query.page || '1';
    let data = fs.readFileSync(`./page/${page}`).toString();
    let render_data = ejs.render(template.replace("PAGE", data));
    res.send(render_data);
});

app.listen(8080, () => {
  console.log(`Listening on http://0.0.0.0:8080`);
});
