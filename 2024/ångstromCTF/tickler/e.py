import requests

url = "https://tickler.web.actf.co"

s = requests.session()

r = s.get(
    f"{url}/api/getFlag",
    headers={"Login": "95d8f04e645b6e949cae2447fb9d49e6:004261d7a4025246b994fb909fc2efdc"}
)
print(r.text)