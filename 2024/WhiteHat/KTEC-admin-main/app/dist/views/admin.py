from flask import Blueprint, render_template, session, abort, request, redirect, url_for, flash
from core.check import loose_waf, strict_waf
from db import dbConnection

bp = Blueprint('admin', __name__, url_prefix='/admin')

@bp.route('/', methods = ['GET', 'POST'])
def admin():
    username = session.get('username')
    
    if request.method == 'POST':
        username = request.form['user']
        password = request.form['pass']
        
        if strict_waf(username):
            return abort(400)
        
        if loose_waf(password):
            return abort(400)

        connection = dbConnection()
        try:
            with connection.cursor() as cursor:
                cursor.execute(f"SELECT * FROM users WHERE username='{username}' AND password='{password}'")
                user = cursor.fetchone()
                if user:
                    session['username'] = user['username']

                    if session.get('username') == 'superadmin':
                        return render_template("flag.html")
                    else:
                        flash("hello admin!!")
                        return redirect(url_for('admin.admin'))
                else:
                    flash("Invalid username/password")
                    return redirect(url_for('admin.admin'))
        except Exception:
            flash("Invalid username/password")
            return redirect(url_for('admin.admin'))
        finally:
            if connection:
                connection.close()
    
    return render_template('admin.html')

@bp.errorhandler(400)
def handle_400_error(_):
    return render_template('400.html'), 400