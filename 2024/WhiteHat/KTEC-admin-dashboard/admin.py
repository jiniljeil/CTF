from flask import Blueprint, render_template, request, redirect, url_for, session, abort, flash
from urllib.parse import urlparse
import subprocess
import socket
import ipaddress

socket.setdefaulttimeout(2)

bp = Blueprint('admin', __name__, url_prefix='/admin')

@bp.route('/', methods = ['GET', 'POST'])
def admin():
    username = session.get('username')

    if username == "superadmin":
        return redirect(url_for('admin.dashboard'))

    if request.method == 'POST':
        username = request.form['user']
        password = request.form['pass']

        if username == "superadmin" and password == "superadmin":
            session['username'] = username
            return redirect(url_for('admin.dashboard'))
        else:
            flash("Invalid username/password")
            return redirect(url_for('admin.admin'))

    return render_template('admin.html')

@bp.route('/dashboard', methods = ['GET'])
def dashboard():
    username = session.get('username')

    if username != 'superadmin':
        return abort(404)

    return render_template('dashboard.html')

@bp.route('/system', methods = ['GET'])
def system():
    username = session.get('username')

    if username != 'superadmin':
        return abort(404)

    return render_template('system.html')

@bp.route('/logout')
def logout():
    session.pop('username', None)
    return redirect(url_for('main.index'))

@bp.route('/read', methods = ['POST'])
def read():
    filename = request.form.get('filename')
    try:
        with open(filename, 'r', encoding='UTF8') as file:
            return render_template('system.html', log_content=file.read())
    except FileNotFoundError:
        abort(404)
    else:
        abort(403)

@bp.route('/ping', methods = ['POST'])
def ping():
    ip = request.form.get('ip')

    if not ip:
        abort(404)

    result = subprocess.run(['ping', '-c', '2', ip], capture_output=True, text=True)
    return render_template('system.html', ping_result=result.stdout)

@bp.route('/curl', methods = ['POST'])
def curl():
    url = request.form.get('url')

    if not url:
        curl_result = 'URL is required'
        return render_template('system.html', curl_result=curl_result)

    if not url.startswith("ifconfig.me"):
        curl_result = 'error'
        return render_template('system.html', curl_result=curl_result)

    for i in url.split():
        if i.startswith("file://"):
            curl_result = 'fail...'
            return render_template('system.html', curl_result=curl_result)

        if not i.startswith(('http://', 'https://')):
            i = i = '//' + i

        parsed_url = urlparse(i)
        netloc = parsed_url.netloc

        if ':' in netloc:
            netloc = netloc.split(':')[0]
        try:
            ip = socket.gethostbyname(netloc)
        except:
            ip = None

        try:
            if ipaddress.ip_address(ip) in ipaddress.ip_network('127.0.0.0/8') or ipaddress.ip_address(ip) in ipaddress.ip_network('172.0.0.0/8') or ip == "0.0.0.0":
                curl_result = 'fail...'
                return render_template('system.html', curl_result=curl_result)
        except:
            pass

    curl_command = ['curl']
    curl_command += url.split()
    try:
        curl_result = subprocess.run(curl_command, capture_output=True, encoding='utf-8', timeout=2)
    except:
        curl_result = "timeout error"
        return render_template('system.html', curl_result=curl_result)
    return render_template('system.html', curl_result=curl_result.stdout)