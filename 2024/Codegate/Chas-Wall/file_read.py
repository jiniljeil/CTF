import requests 

url = "http://3.39.6.7:8000"

s = requests.session() 
s.cookies.set("PHPSESSID", "deb9ltqtgf75bnq4k677v6unam",domain=f"{url[7:]}")

r = s.get(
    f"{url}/uploads/fb7ba2078c91ec9b52730e624d6f5ed2/test.php",
)
if r.status_code == 200:
    print(r.text) 