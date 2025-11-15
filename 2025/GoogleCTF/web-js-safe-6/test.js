const http = require('http');
const url = require('url');

const server = http.createServer((req, res) => {
  const query = url.parse(req.url, true).query;
  const input = query.username || 'guest';

  // CRLF Injection 시뮬레이션
  const headers = `HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nSet-Cookie: username=${input}\r\n\r\n`;

  // 로우 HTTP 응답을 직접 작성 (주의: 여기선 res 사용 안 함)
  res.socket.write(headers + 'Hello ' + input);
  res.socket.end();
});

server.listen(3000, () => {
  console.log('Raw HTTP Server running on http://localhost:3000');
});
