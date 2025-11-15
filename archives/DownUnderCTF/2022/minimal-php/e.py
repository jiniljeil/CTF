import requests 

url = "http://localhost:1337" 

r = requests.post(
    f"{url}",
    data={"0": "http://www.example.com/"}
)

print(r.status_code)
print(r.text)