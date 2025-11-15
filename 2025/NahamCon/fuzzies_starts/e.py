from scapy.all import IP, TCP, sr1
import time

TARGET_IP = "137.184.230.90"
TARGET_PORT = 8080
TIMEOUT = 2

def try_ipid(ipid):
    ip = IP(dst=TARGET_IP, id=ipid)
    syn = TCP(dport=TARGET_PORT, flags='S', sport=12345)
    pkt = ip / syn

    response = sr1(pkt, timeout=TIMEOUT, verbose=0)

    if response:
        if response.haslayer(TCP):
            flags = response.getlayer(TCP).flags
            if flags == 0x12:  # SYN-ACK
                print(f"[+] IPID {ipid} → SYN-ACK received (Port OPEN)")
            elif flags == 0x14:  # RST-ACK
                print(f"[+] IPID {ipid} → RST received (Filtered or Closed)")
            else:
                print(f"[?] IPID {ipid} → TCP flags: {flags}")
        else:
            print(f"[?] IPID {ipid} → Non-TCP response: {response.summary()}")
    else:
        print(f"[-] IPID {ipid} → No response")

if __name__ == "__main__":
    for ipid in range(0, 65536):
        try_ipid(ipid)

