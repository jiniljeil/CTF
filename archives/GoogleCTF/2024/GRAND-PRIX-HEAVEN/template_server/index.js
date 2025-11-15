const http = require('http');

const templates = require('./templates');

const parseMultipartData = (data, boundary) => {
  var chunks = data.split(boundary);
  // always start with the <head> element
  var processedTemplate = templates.head_start;

  // to prevent loading an html page of arbitrarily large size, limit to just 7 at a time
  let end = 7;
  if (chunks.length - 1 <= end) {
    end = chunks.length - 1;
  }
  for (var i = 1; i < end; i++) {
    // seperate body from the header parts
    var lines = chunks[i].split('\r\n\r\n')
      .map((item) => item.replaceAll("\r\n", ""))
      .filter((item) => { return item != '' })

    for (const item of Object.keys(templates)) {
      if (lines.includes(item)) {
        processedTemplate += templates[item];
      }
    }
  }
  return processedTemplate;
}


const reqHandler = function (req, res) {
  res.setHeader("Content-Type", "text/html");
  var result;
  if (req.method == 'POST') {
    var body = ''
    req.on('data', function (data) {
      body += data
    })
    req.on('end', function () {
      var boundary = '--' + req.headers['content-type'].split("boundary=")[1];
      console.log("boundary", boundary);

      result = parseMultipartData(body, boundary);
      console.log("result", result);
      res.end(result);
    })
  } else {
    res.writeHead(400);
    return res.end();
  }

};

const server = http.createServer(reqHandler);
server.listen(9999, () => {
  console.log('Server running at <http://localhost:9999/>');
});