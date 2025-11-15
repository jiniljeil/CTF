from scapy.all import IP, TCP, send, sr1
import threading
import time
import queue

TARGET_IP = "137.184.230.90"
TARGET_PORT = 8080
TIMEOUT = 1
THREADS = 20
MY_PORT = 12345

ipid_queue = queue.Queue()

# IPID 값을 큐에 추가
for ipid in range(0, 65536):
    ipid_queue.put(ipid)

def worker():
    while not ipid_queue.empty():
        ipid = ipid_queue.get()
        ip = IP(dst=TARGET_IP, id=ipid)
        syn = TCP(dport=TARGET_PORT, flags='S', sport=12345)
        pkt = ip / syn

        response = sr1(pkt, timeout=TIMEOUT, verbose=0)
        if response and response.haslayer(TCP):
            flags = response.getlayer(TCP).flags
            if flags == 0x12:  # SYN-ACK
                print(f"[+] IPID {ipid} → SYN-ACK received (Port OPEN)")
                ip = IP(dst=TARGET_IP, id=ipid)
                syn = TCP(sport=MY_PORT, dport=TARGET_PORT, flags="S", seq=1000)
                syn_ack = sr1(ip/syn, timeout=2)

                if not syn_ack:
                    print("No response from SYN, maybe IPID is wrong or target down.")
                    exit()

                # 2. ACK
                ack = TCP(sport=MY_PORT, dport=TARGET_PORT, flags="A",
                seq=syn_ack.ack, ack=syn_ack.seq + 1)

                send(ip/ack)

                # 3. HTTP GET 요청
                http_payload = b"GET / HTTP/1.1\r\nHost: 137.184.230.90\r\n\r\n"
                psh = TCP(sport=MY_PORT, dport=TARGET_PORT, flags="PA",
                seq=syn_ack.ack, ack=syn_ack.seq + 1)
                response = sr1(ip/psh/http_payload, timeout=3)

                if response:
                    print("==== RESPONSE ====")
                    print(bytes(response.payload))

            elif flags == 0x14:  # RST received
                print(f"[+] IPID {ipid} → RST received (Filtered or Closed)")
        ipid_queue.task_done()

if __name__ == "__main__":
    threads = []

    for _ in range(THREADS):
        t = threading.Thread(target=worker)
        t.daemon = True
        threads.append(t)
        t.start()

    ipid_queue.join()
    print("Scanning complete.")

