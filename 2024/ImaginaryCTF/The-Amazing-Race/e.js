
async function gogo(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/move?id=" + id + "&move=down", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send("");
}

for (let i = 0; i < 5; i++) {
    gogo("2d0df296-8500-47b8-8ff2-220188ee11b8");
    gogo("2d0df296-8500-47b8-8ff2-220188ee11b8");
}