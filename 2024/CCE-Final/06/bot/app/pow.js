const crypto = require('crypto');
const { CONFIG } = require("./config");

function hash(str) {
    return crypto.createHash(CONFIG.pow.hash).update(str).digest('hex');
}

function randHexStr(len) {
    return crypto.randomBytes(Math.floor((len + 1) / 2)).toString('hex').slice(0, len);
}

function format(salt, answer) {
    return CONFIG.pow.chall_mask.replace("#S", salt).replace("#A", answer);
}

function getPow() {
    if (!CONFIG.pow.required)
        return null;

    let level = CONFIG.pow.level;
    let salt_size = CONFIG.pow.salt_size

    let answer = randHexStr(level * 2);
    let salt = randHexStr(salt_size * 2);
    let challenge = hash(format(salt, answer));
    let challenge_mask = format(salt, `<strong><u>${CONFIG.pow.answer_mask.repeat(level * 2)}</u></strong>`);
    challenge_mask = `${CONFIG.pow.hash}("${challenge_mask}")`
    
    console.log(`PoW issued: ${CONFIG.pow.hash}("${format(salt, answer)}") == "${challenge}"`);
    return { salt, level, challenge, challenge_mask, checked: false }
}

function checkPow(input, pow) {
    if (!CONFIG.pow.required)
        return false;
    
    if (typeof input !== "string")
        return false;
    if (pow.checked)
        return false;

    return hash(format(pow.salt, input)) == pow.challenge
}

module.exports = { getPow, checkPow };