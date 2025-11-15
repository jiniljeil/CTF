import requests 
from bs4 import BeautifulSoup


URL = "http://localhost:3000" 
# URL = "http://34.89.179.154:30297"

r = requests.get(
    f"{URL}/awaken"
)
soup = BeautifulSoup(r.text, 'html.parser')
csrf_token = soup.find('input', {'name': '_csrf'})['value']
print(csrf_token)

r = requests.post(
    f"{URL}/login", 
    data={
        "_csrf": csrf_token, 
        "username": "", 
        "password": ""
    }
)
print(r.status_code)
print(r.text)