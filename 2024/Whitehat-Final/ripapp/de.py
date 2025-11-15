from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
import base64

def decrypt(encrypted_str, key, iv):
    # Base64 URL-safe 디코딩
    encrypted_str = encrypted_str + "=="[(4 - (len(encrypted_str) % 4)) % 4:]  # Base64 패딩 추가
    encrypted_data = base64.urlsafe_b64decode(encrypted_str)  # URL-safe Base64 디코딩
    
    # AES 객체 초기화 (CBC 모드)
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    
    # 복호화 및 패딩 제거
    decrypted_data = unpad(cipher.decrypt(encrypted_data), AES.block_size)
    
    return decrypted_data.decode('utf-8')

# 예시 key와 iv (16바이트 길이의 문자열)
key = "1234567890123456"  # Java에서 사용한 key와 동일
iv = "1234567890123456"   # Java에서 사용한 IV와 동일

# 암호화된 Base64 URL-safe 문자열 (제공된 데이터)
encrypted_str = "fbMYqUgWIob39ES7MIvKKpRgKEsWfhVQX5zGasWOYX2JgscqNmyfXu7yi4-BVq6sywx9T9M7xVyu4ZVcmWUVnE_xb0CAB-WyAUFC6WRE3hwXD0LCpCA9Z5ZVyXc5KYT7O2Vt70WMr2g4C--61jeHamT6emmUW6_OUh8vVucgXFVR43apasP0yp_E3HIx3hT7P-nsoEKhF0lMu9_ym6WEuKiE3w3cijPsrnsVQ7R1RT-lJo20l3m0Q5byR51wfRD5-EypvtzTPkdQ9qEsrylv4Q"

# 복호화
decrypted_str = decrypt(encrypted_str, key, iv)
print("Decrypted text:", decrypted_str)
