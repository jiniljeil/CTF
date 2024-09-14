const sleep = (d) => new Promise((r) => setTimeout(r, d));

async function appendFileInfo(id) {
    const ul = document.querySelector("#filesList");
    const row = document.createElement("a");
    row.className = "list-group-item list-group-item-action";
    row.href = "#" + id;
    row.id = "file-" + id;
    row.innerText = GAMES[id].name;
    ul.appendChild(row);
}

async function previewFile(body, metadata) {
    console.log(metadata);
    await window.safeFrameRender(body, "text/html;charset=utf-8", metadata);
}
