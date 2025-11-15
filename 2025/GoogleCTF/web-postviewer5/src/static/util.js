if (!crypto.subtle) {
  import(
    "https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.11.0/sha256.min.js"
  );
  crypto.subtle = {
    digest: async function (c, body) {
      var hash = sha256.create();
      hash.update(body);
      return hash.arrayBuffer();
    },
  };
}

function getRandom(n) {
  return Array.from(Array(n), Math.random).map(e=>e.toString(36).slice(2)).join('');
};

async function generateFileId(file) {
  const hash = await crypto.subtle.digest('SHA-256', await file.arrayBuffer());
  const strHash = arrayToBase36(new Uint8Array(hash)).slice(0, 50);
  const name = file.name.slice(0, 50);
  if (/^[a-z0-9]+$/.test(name) && name.length > strHash.length) {
    return name;
  }
  return strHash;
}

async function appendFileInfo(fileInfo) {
  const ul = document.querySelector("#filesList");
  const row = document.createElement("a");
  row.className = "list-group-item list-group-item-action";
  const fileId = fileInfo.id;
  row.href = "#file-" + fileId;
  row.id = "file-" + fileId;
  let hasBadge = false;
  const badgesSpan = document.createElement('span');
  badgesSpan.classList.add('badge');
  if (fileInfo.isPublic) {
    hasBadge = true;
    badgesSpan.classList.add('bg-success');
    badgesSpan.innerText = 'public';
  }
  if (fileInfo.cached) {
    hasBadge = true;
    badgesSpan.classList.add('bg-secondary');
    badgesSpan.innerText = 'cached';
  }
  row.innerText = fileInfo.name;
  if (hasBadge) {
    row.appendChild(badgesSpan);
  }
  row.dataset.name = fileInfo.name;
  row.dataset.id = fileInfo.id;
  ul.appendChild(row);
}

mathRandomInvocations = []
async function renderFile({ id, cached, file }, safeFrameIframe) {
  let salt;
  const encoder = new TextEncoder();
  if(cached){
    salt = encoder.encode(id).buffer;
  } else {
    const rand = getRandom(5);
    mathRandomInvocations.push(rand);
    salt = encoder.encode(rand).buffer;
  }
  return window.safeFrameRender({
    body: await file.arrayBuffer(), mimeType: file.type, salt, cached
  }, safeFrameIframe);
}
function scaleIframe(val, safeFrameIframe) {
  const scaleSpan = document.querySelector("#scaleSpan");
  if (safeFrameIframe === null) return;
  let scale =
    Number(safeFrameIframe.style.transform.match(/scale\(([^)]+)\)/)?.[1]) || 1;
  scale += val;
  if (scale <= 0.2) scale = 0.2;
  safeFrameIframe.style.transformOrigin = "0 0";
  safeFrameIframe.style.transform = `scale(${scale})`;
  safeFrameIframe.style.width = 100 / scale + "%";
  safeFrameIframe.style.height = 100 / scale + "%";
  scaleSpan.innerText = Math.floor(scale * 100) + "%";
}
