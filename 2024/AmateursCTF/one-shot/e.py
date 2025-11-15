# amateursCTF{go_union_select_a_life}
import requests 

url = "http://one-shot.amt.rs"
r = requests.post(f"{url}/guess", 
                  data={
                      "id":"2f78e058112a0008",
                      "password":"0864f8ee1ae1e11a9753a5bdb1aa199e"
                  })
print(r.text)