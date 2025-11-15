const typingDelay = 100; // 글자당 타이핑 속도(ms)
const wordDelay = 120;   // 단어 간 간격(ms)
const sourceSelector = ".text-gray-400";

// === 코드 ===
(async () => {
    const spans = document.querySelectorAll(sourceSelector);
    if (!spans.length) return console.error("❌ .text-gray-400 요소를 찾을 수 없습니다.");

    const words = Array.from(spans).map(el => el.textContent.trim()).filter(Boolean);
    console.log("단어 목록:", words);

    const target = document.activeElement;
    if (!target) return console.error("❌ 타이핑할 요소를 클릭(포커스)해주세요.");
    target.focus();

    for (const [idx, word] of words.entries()) {
        // 각 단어를 한 글자씩 타이핑
        for (const ch of word) {
            const keyEventProps = { key: ch, bubbles: true, cancelable: true };
            target.dispatchEvent(new KeyboardEvent("keydown", keyEventProps));
            await new Promise(r => setTimeout(r, typingDelay));
        }
    }

    console.log("✅ 모든 단어 입력 완료!");
})();