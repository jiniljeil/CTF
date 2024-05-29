import requests 
s = "xxxxxxxxx.xxxx.x.xxxxxxxxxx.x.x.x.xxxxxxxxxx.xxx.xxxxxxxxxx.x.x.x.x.xxxxxxxxxx.x.x.x.x.xxxxxxxxxx.x.x.x.xxxxxxxxxx.x.x.x.x.x.xxxx.xxxxxxxxxx.xxxxx.xxxxx.xxxxx.xxxxxxxxxx"
sentence = s.split(".")
# for i in sentence:
#     print(len(i))    

# https://www.w3.org/Style/XSL/TestSuite/results/4/XEP/symbol.pdf

chars = "abcdefghijklmnopqrstuvwxyz"

url = "https://ctf.amateurs.team/api/v1/challs/bcds-web-agile-rut/submit"
headers = {
    "Authorization": "Bearer HMZeMIajnQulPojwL6LWCAiuyOZfg5A3bRYOpuUIEHCLypbfVgMz06KlJ5OX3wzoa9MCwGgdN5yn69wSl/LKJEQEcrPRYar0CjsukgordcH6HXHfPJzjwHEKGxM5"
}

f = open("flaglist.txt", "w")

for a in chars:
    for b in chars: 
        k = "amateursCTF{0k_but_1_dont_like_the_"+a+b+"mon0_===}"
        print(k)
        f.write(k+"\n")
                    # r = requests.post(url, headers=headers,
                    # json={
                    #     "flag": k #"amateursCTF{0k_but_1_dont_like_the_jason0_===}"
                    # })
                    # print(r.text)
                    # if not "badFlag" in r.text:
                    #     exit(0)