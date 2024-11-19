from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
import base64

# AES 알고리즘 및 CBC 모드로 복호화하는 함수
def decrypt(encrypted_str, key, iv):
    # Base64로 인코딩된 문자열을 디코딩
    encrypted_str = encrypted_str + "=="[(4 - (len(encrypted_str) % 4)) % 4:]  # 패딩을 맞추기 위해 '==' 추가
    encrypted_data = base64.urlsafe_b64decode(encrypted_str)  # URL-safe Base64 디코딩
    
    # AES 객체 초기화
    cipher = AES.new(key.encode(), AES.MODE_CBC, iv.encode())
    
    # 복호화 및 패딩 제거
    decrypted_data = unpad(cipher.decrypt(encrypted_data), AES.block_size)
    
    return decrypted_data.decode('utf-8')

# 예시 key와 iv (Java 코드와 동일하게 16바이트 길이의 문자열 사용)
key = "1234567890123456"  # Java에서 사용한 key와 동일
iv = "1234567890123456"   # Java에서 사용한 IV와 동일

# 암호화된 문자열 (Java에서 나온 출력값을 입력)
encrypted_str = "fbMYqUgWIob39ES7MIvKKpRgKEsWfhVQX5zGasWOYX2JgscqNmyfXu7yi4-BVq6sywx9T9M7xVyu4ZVcmWUVnE_xb0CAB-WyAUFC6WRE3hwXD0LCpCA9Z5ZVyXc5KYT7O2Vt70WMr2g4C--61jeHamT6emmUW6_OUh8vVucgXFVR43apasP0yp_E3HIx3hT7P-nsoEKhF0lMu9_ym6WEuKiE3w3cijPsrnsVQ7R1RT-lJo20l3m0Q5byR51wfRD5-EypvtzTPkdQ9qEsrylv4Q "

# 복호화
decrypted_str = decrypt(encrypted_str, key, iv)
print("Decrypted text:", decrypted_str)
print()

# {"list":["FAHoezFX3Beuxz46nuZDJSJHxa1XC0","yQOIIWf3PzTrvh0NdUByunaZZCGTYA","Rc8AAnDk0myfDAK9QU5aNk2gWG1Tfx","yWHUOv7if1lqbMCMKfYBOrHS8yrTIA"]}