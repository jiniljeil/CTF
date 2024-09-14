const evaluatorHtml = `
<html>
  <head>
    <meta charset="utf-8">
    <title>Evaluator</title>

    <script>
      onmessage = e => {
        if(e.source !== parent) {
          throw /not parent/;
        };
        if(e.data.eval){
          eval(e.data.eval);
        }
      }
      onload = () => {
        parent.postMessage('loader ready','*');
      }
    </script>

    <style>
      body{
        padding: 0px;
        margin: 0px;
      }
      iframe{
        width: 100vw;
        height: 100vh;
        border: 0;
      }
      .spinner {
        background: url(https://storage.googleapis.com/gctf-postviewer/spinner.svg) center no-repeat;
      }
      .spinner iframe{
        opacity: 0.2
      }
    </style>
  </head>
  <body>
    <div id="container" class="spinner"></div>
  </body>
</html>
`;

const iframeInserterHtml = `
const container = document.querySelector("#container");
container.textContent = '';
const iframe = document.createElement('iframe');
iframe.src = URL.createObjectURL(new Blob([e.data.body], {type: e.data.type}));
if(e.data.sandbox) {
  iframe.sandbox = e.data.sandbox;
}
container.appendChild(iframe);
setTimeout(()=>{
  container.classList.remove('spinner');
}, 5000);
iframe.onload = () => {
  setTimeout(()=>{
    container.classList.remove('spinner');
  }, 500);
};
`;

const sleep = (d) => new Promise((r) => setTimeout(r, d));

async function sha1(message) {
    const buffer = new TextEncoder().encode(message);
    const hashBuffer = await crypto.subtle.digest("SHA-1", buffer);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    return hashArray.map((b) => b.toString(16).padStart(2, "0")).join("");
}

async function appendFileInfo(fileInfo) {
    const ul = document.querySelector("#filesList");
    const row = document.createElement("a");
    row.className = "list-group-item list-group-item-action";
    const fileId = await sha1(fileInfo.name);
    row.href = "#file-" + fileId;
    row.id = "file-" + fileId;
    row.innerText = fileInfo.name;
    row.dataset.name = fileInfo.name;
    ul.appendChild(row);
}
async function previewFile(filePromise, container) {
    const shimOrigin = location.origin;
    const { safeFrame, safeFrameOrigin } = await safeFrameRender(
        evaluatorHtml,
        "text/html; charset=utf-8",
        "postviewer",
        shimOrigin,
        container
    );
    const onReady = async function (e) {
        if (e.origin !== safeFrameOrigin || e.data !== "loader ready") return;

        const file = await filePromise;
        const body = await file.arrayBuffer();
        let sandbox;
        if (file.type !== "application/pdf") {
            sandbox = "allow-scripts";
        }
        safeFrame.contentWindow.postMessage(
            { eval: iframeInserterHtml, body, type: file.type, sandbox },
            safeFrameOrigin,
            [body]
        );
        window.removeEventListener("message", onReady);
    };
    window.addEventListener("message", onReady);
}

function scale(val) {
    const previewIframeDiv = document.querySelector("#previewIframeDiv");
    const iframe = previewIframeDiv.querySelector("iframe");
    const scaleSpan = document.querySelector("#scaleSpan");
    if (iframe === null) return;
    let scale =
        Number(iframe.style.transform.match(/scale\(([^)]+)\)/)?.[1]) || 1;
    scale += val;
    if (scale <= 0.2) scale = 0.2;
    iframe.style.transformOrigin = "0 0";
    iframe.style.transform = `scale(${scale})`;
    iframe.style.width = 100 / scale + "%";
    iframe.style.height = 100 / scale + "%";
    scaleSpan.innerText = Math.floor(scale * 100) + "%";
}
