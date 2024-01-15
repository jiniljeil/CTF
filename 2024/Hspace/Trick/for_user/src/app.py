import os
import socket
import time
from flask import (Flask, flash, redirect, render_template, render_template_string, request, session, make_response)

app = Flask(__name__)

reportIp = {}

app.config["SECRET_KEY"] = os.urandom(32)

@app.route("/")
def index():
    name = request.args.get('name', "").replace("(","").replace(")","").replace("{","").replace("}","")
    code = request.args.get('code', "").replace("(","").replace(")","").replace("{","").replace("}","")
    title = request.args.get('title', "")
    content = request.args.get('content', "")
    ctx = {
        'nonce': os.urandom(32).hex(),
        'note': {
        'title': title,
        'content': content,
        },
        'name': name,
        'code': code,
        'is_admin': 0
    }
    return render_template("index.html", **ctx)


@app.route("/report")
def bot():
    url = request.args.get('url', None)
    if url:
        try:
            if reportIp[request.remote_addr] and reportIp[request.remote_addr] < time.time() - 15:
                reportIp[request.remote_addr] = time.time()       
                bot = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
                bot.connect(("bot", 5000))
                bot.send(url.encode())
                bot.close()
            else:
                return "Too fast"
        except KeyError:
            reportIp[request.remote_addr] = time.time()       
            bot = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
            bot.connect(("bot", 5000))
            bot.send(url.encode())
            bot.close()
    else:
        return "/report?url=http://"
    return "Report success"

if __name__ == "__main__":
    app.run(host="0.0.0.0",port='8080', debug=False)