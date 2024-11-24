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
            return True
    return False

def strict_waf(data):
    for keyword in strict_keywords:
        if keyword in data.lower():
            return True
    return False