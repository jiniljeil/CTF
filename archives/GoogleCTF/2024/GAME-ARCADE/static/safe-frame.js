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

function arrayToBase36(arr) {
    return arr
        .reduce((a, b) => BigInt(256) * a + BigInt(b), BigInt(0))
        .toString(36);
}

function concatBuffers(...buffers) {
    let length = 0;
    for (const buf of buffers) {
        length += buf.byteLength;
    }
    const newBuf = new Uint8Array(length);
    let offset = 0;
    for (const buf of buffers) {
        newBuf.set(new Uint8Array(buf), offset);
        offset += buf.byteLength;
    }
    return newBuf.buffer;
}

async function calculateHash(...parts) {
    const encoder = new TextEncoder();
    const newParts = [];
    for (let i = 0; i < parts.length; i++) {
        const part = parts[i];
        if (typeof part === "string") {
            newParts.push(encoder.encode(part).buffer);
        } else {
            newParts.push(part);
        }
        if (i < parts.length - 1) {
            newParts.push(encoder.encode("$@#|").buffer);
        }
    }
    const buffer = concatBuffers(...newParts);
    const hash = await crypto.subtle.digest("SHA-256", buffer);
    return arrayToBase36(new Uint8Array(hash)).padStart(50, "0").slice(0, 50);
}

window.safeFrameRender = async function safeFrameRender(
    body,
    mimeType,
    metadata
) {
    const product = "google-ctf";

    const hash = await calculateHash(product, body, window.origin);
    const url = new URL(
        `https://${hash}-h641507400.scf.usercontent.goog/google-ctf/shim.html`
    );
    url.searchParams.set("origin", window.origin);
    url.searchParams.set("cache", "1");

    const width = metadata?.width || screen.width * 0.8;
    const height = metadata?.height || screen.height * 0.8;
    const left = (screen.width - width) / 2;
    const top = (screen.height - height) / 2;
    const safeWindow = window.open(
        url,
        "_blank",
        `width=${width}, height=${height}, top=${top}, left=${left}`
    );

    const loadedPromise = new Promise((resolve) => {
        const interval = setInterval(() => {
            const messageChannel = new MessageChannel();
            messageChannel.port1.onmessage = () => {
                resolve();
                clearInterval(interval);
            };
            safeWindow.postMessage(1, url.origin, [messageChannel.port2]);
        }, 100);
    });

    loadedPromise.then(() => {
        const messageChannel = new MessageChannel();
        messageChannel.port1.onmessage = (e) => {
            console.log(e.data);
        };
        safeWindow.postMessage(
            { body, mimeType, salt: new TextEncoder().encode(body).buffer },
            url.origin,
            [messageChannel.port2]
        );
    });

    return { safeWindow, safeFrameOrigin: url.origin, loadedPromise };
};
