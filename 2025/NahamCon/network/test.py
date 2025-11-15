from scapy.all import IP, TCP, send, sr1
import time

TARGET_IP = "137.184.230.90"
TARGET_PORT = 8080
MY_PORT = 12345
VALID_IPID = 2004  # 예시

# 1. SYN
ip = IP(dst=TARGET_IP, id=VALID_IPID)
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
else:
    print("No HTTP response.")

