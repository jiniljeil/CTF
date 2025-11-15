import "./untrusted_content.js";

const untrustedContent = document.querySelector("untrusted-content");
const textarea = document.querySelector("textarea");
const shareUrlAnchor = document.getElementById("share-url");

function update() {
  const html = textarea.value;
  untrustedContent.html = html;
  const shareUrl = `/share?body=${encodeURIComponent(html)}`;
  shareUrlAnchor.href = shareUrl;
}

textarea.addEventListener("input", () => update());
update();
