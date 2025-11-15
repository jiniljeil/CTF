from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
import base64

# AES 암호화 함수
def encrypt(data, key, iv):
    # AES 객체 생성 (CBC 모드)
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    
    # 데이터 패딩 (PKCS7 padding, AES 블록 크기에 맞춰 패딩을 추가)
    padded_data = pad(data.encode(), AES.block_size)
    
    # 암호화
    encrypted = cipher.encrypt(padded_data)
    
    # Base64 URL-safe 인코딩 (Java와 동일한 형식으로 변환)
    encoded = base64.urlsafe_b64encode(encrypted).decode('utf-8').rstrip('=')
    
    return encoded

# 예시 키와 IV (16바이트 길이)
key = "1234567890123456"  # Java에서 사용한 key와 동일
iv = "1234567890123456"   # Java에서 사용한 IV와 동일

# 암호화할 문자열
text_to_encrypt = "Hello, World!"

# 암호화 실행
encrypted_text = encrypt(text_to_encrypt, key, iv)

print("Encrypted text:", encrypted_text)
