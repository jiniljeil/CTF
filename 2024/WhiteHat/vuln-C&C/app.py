from flask import Flask, render_template, abort, request, abort, send_file
from pathlib import Path
import os
import datetime as dt

app = Flask(__name__)

app.secret_key = os.urandom(128).hex()

def getReadableByteSize(num) -> str:
    for unit in ['', 'K', 'M', 'G', 'T', 'P', 'E', 'Z']:
        if abs(num) < 1024.0:
            if num == int(num):
                return "%d%s" % (int(num), unit) 
            else:
                return "%3.1f%s" % (num, unit)
        num /= 1024.0
    
    if num == int(num):
        return "%d%s" % (int(num), unit) 
    else:
        return "%.1f%s" % (num, 'Y')
    
def getTimeStampString(tSec: float) -> str:
    tObj = dt.datetime.fromtimestamp(tSec)
    tStr = dt.datetime.strftime(tObj, '%Y-%m-%d %M:%S')
    return tStr

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/', defaults={'reqPath': ''})
@app.route('/<path:reqPath>')
def directory_listing(reqPath):
    FolderPath = app.root_path
    absPath = os.path.join(FolderPath, reqPath)
    if not os.path.exists(absPath):
        return abort(404)

    def fObjFromScan(x):
        fileStat = x.stat()
        return {'name': x.name,
                'relPath': os.path.relpath(x.path, FolderPath).replace("\\", "/"),
                'mTime': getTimeStampString(fileStat.st_mtime),
                'size': getReadableByteSize(fileStat.st_size),
                'isdir': os.path.isdir(x.path),
                }
    if os.path.isdir(absPath):
        fileObjs = [fObjFromScan(x) for x in os.scandir(absPath)]
    else:
        abort(404)

    parentFolderPath = os.path.relpath(
        Path(absPath).parents[0], FolderPath).replace("\\", "/")
    
    return render_template('list.html', data={'files': fileObjs, 'parentFolder': parentFolderPath, 'req_path': request.path})

@app.route('/download', methods=['GET'])
def download():
    filename = request.args.get('filename')

    if not filename:
        abort(400)

    file_path = os.path.join('static', filename)
    
    if not os.path.isfile(file_path):
        return abort(404)
    
    return send_file(file_path, as_attachment=True)
    
if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000)