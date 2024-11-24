from urllib.parse import urlparse
import subprocess
import socket
import ipaddress

url = "http://ifconfig.me || /flag"

for i in url.split():
    if i.startswith("file://"):
        curl_result = 'fail...'
        print(curl_result)
        exit()

    if not i.startswith(('http://', 'https://')):
        i = i = '//' + i

    parsed_url = urlparse(i)
    netloc = parsed_url.netloc
    print(i, netloc)
    
    if ':' in netloc:
        netloc = netloc.split(':')[0]
    try:
        ip = socket.gethostbyname(netloc)
    except:
        ip = None

    try:
        if ipaddress.ip_address(ip) in ipaddress.ip_network('127.0.0.0/8') or ipaddress.ip_address(ip) in ipaddress.ip_network('172.0.0.0/8') or ip == "0.0.0.0":
            curl_result = 'fail...1'
            print(curl_result)
            exit()
    except:
        pass

curl_command = ['curl']
curl_command += url.split()
try:
    curl_result = subprocess.run(curl_command, capture_output=True, encoding='utf-8', timeout=2)
    print(curl_result)
except:
    curl_result = "timeout error"
