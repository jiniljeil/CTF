import requests

url = "http://3.36.67.87:7777"

r = requests.post(
    f"{url}/", 
    data={
        "username": "helloworld5", 
        "password": "helloworld5"
    }
    cookies={"Session": "helloworld5"}
)
