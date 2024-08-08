from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.padding import PKCS7
from cryptography.hazmat.backends import default_backend
import base64

def aes_decrypt(ciphertext, key, iv):
    # Decode the base64 encoded ciphertext
    ciphertext = base64.b64decode(ciphertext)
    
    # Create a cipher object using the key and IV
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    
    # Create a decryptor object
    decryptor = cipher.decryptor()
    
    # Perform the decryption
    padded_plaintext = decryptor.update(ciphertext) + decryptor.finalize()
    
    # Unpad the plaintext
    unpadder = PKCS7(algorithms.AES.block_size).unpadder()
    plaintext = unpadder.update(padded_plaintext) + unpadder.finalize()
    
    return plaintext

# Example usage
ciphertext_base64 = "M/AGmnYDZRQIOO6+xXpxwtZHpwE81Z6cYwf5B0xgyWA="
key = b"FdAZDF4AOakSXyNKUvqFWqCZUhtMn7z9"  # 32 bytes key for AES-256
iv = b"i8CiEyP0xHqkXnTX"  # 16 bytes IV

try:
    plaintext = aes_decrypt(ciphertext_base64, key, iv)
    print("Decrypted text:", plaintext.decode('utf-8'))
    # Decrypted text: ACCE2024ck03k1#!jk#14al
except Exception as e:
    print("An error occurred during decryption:", e)