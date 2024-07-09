from flask import Flask, request
import json
app = Flask(__name__)

@app.route("/", methods=['GET', 'POST'])
def index():
    print(request.path)
    # data = json.loads(request.data)
    # print(data, data["policy"])
    return "test"
	
if __name__ == '__main__':
    app.run()
