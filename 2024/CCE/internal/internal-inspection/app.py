from flask import Flask, render_template, request, send_file
from flask_sqlalchemy import SQLAlchemy
from lxml import etree
import pandas as pd
import io, os

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{os.path.dirname(__file__)}/instance/members.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

class Member(db.Model):
    __tablename__ = 'members'
    
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    address = db.Column(db.String(200))
    company = db.Column(db.String(100))
    job = db.Column(db.String(100))
    email = db.Column(db.String(100))
    username = db.Column(db.String(50))

class Pay(db.Model):
    __tablename__ = 'pay'
    
    id = db.Column(db.Integer, primary_key=True)
    uuid = db.Column(db.String(36))
    name = db.Column(db.String(100))
    team = db.Column(db.String(100))
    salary = db.Column(db.Integer)

class Notice(db.Model):
    __tablename__ = 'notice'
    
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(100))
    content = db.Column(db.String(500))
    date = db.Column(db.Date)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/member')
def member():
    members = Member.query.all()
    return render_template('member.html', members=members)

@app.route('/pay')
def pay():
    pays = Pay.query.all()
    return render_template('pay.html', pays=pays)

@app.route('/notice', methods=['GET'])
def notice():
    search_query = request.args.get('q', '').strip().lower()

    if search_query:
        filtered_notices = Notice.query.filter(Notice.title.ilike(f'%{search_query}%')).all()
        if filtered_notices:
            return render_template('notice_list.html', filtered_notices=filtered_notices, search_query=search_query)
        else:
            return render_template('notice_list.html', no_data=True, search_query=search_query)
    else:
        notices = Notice.query.all()
        return render_template('notice_list.html', notices=notices)

@app.route('/notice/<int:notice_id>')
def notice_detail(notice_id):
    notice = Notice.query.get(notice_id)
    return render_template('notice_detail.html', notice=notice)

@app.route('/download', methods=['POST'])
def download():
    xml_file = request.files['file']
    xml_data = xml_file.read()
    xml_data = xml_data.decode('UTF-8')
    xml_data = xml_data.replace("SYSTEM", "system")
    xml_data = xml_data.encode('UTF-8')
    
    parser = etree.XMLParser(encoding='UTF-8')
    try:
        root = etree.fromstring(xml_data, parser=parser)
    except:
        root = etree.fromstring("<name>fail</name>", parser=parser)
    
    data = []
    try:
        for member in root.findall('member'):
            name = member.find('name').text
            address = member.find('address').text
            company = member.find('company').text
            job = member.find('job').text
            email = member.find('email').text
            username = member.find('username').text
            data.append([name, address, company, job, email, username])
        
        df = pd.DataFrame(data, columns=['Name', 'Address', 'Company', 'Job', 'Email', 'Username'])
        
        output = io.BytesIO()
        df.to_excel(output, index=False, engine='openpyxl')
        output.seek(0)
    except:
        output = str()
    
    return send_file(output, as_attachment=True, mimetype='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', download_name='members.xlsx')

@app.route('/pay_download', methods=['POST'])
def pay_download():
    pays = Pay.query.all()
    df = pd.DataFrame([(pay.uuid, pay.name, pay.team, pay.salary) for pay in pays], columns=['UUID', 'Name', 'Team', 'Salary'])
    
    output = io.BytesIO()
    df.to_excel(output, index=False, engine='openpyxl')
    output.seek(0)
    
    return send_file(output, as_attachment=True, mimetype='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', download_name='pays.xlsx')

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5000)
