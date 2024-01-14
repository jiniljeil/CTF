from flask import Flask, request, render_template, render_template_string, redirect
import subprocess
import urllib
import re

app = Flask(__name__)

blacklist = ['os','subprocesses','exec','vars','sys','"','\+',
             'open','rm','main','static','templates','ctf','rf',
             'spawnlp','execfile','dir','dev','tcp','sh','import',
             'built','__class__','for','request','\,','app','file',
             'url_for','\[','\]','config']

def Prevent_SSTI(input):
    for i in blacklist:
        res = re.search(i,input)
        if res:
            return True
    else:
        return False

@app.route('/')
def main():
    name = request.args.get("name", "World")
    return render_template_string(f'Hello {name}!!')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)