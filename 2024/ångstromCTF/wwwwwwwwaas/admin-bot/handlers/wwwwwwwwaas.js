const puppeteer = require("puppeteer");

const sleep = s => new Promise(res => setTimeout(res, s * 1000));

module.exports = {
    name: 'wwwwwwwwaas',
    timeout: 45000,
    noContext: true,
    async execute(_, url) {
        const key = process.env.CHALL_WWWWWWWWAAS_KEY || "placeholder";
        const domain = process.env.CHALL_WWWWWWWWAAS_DOMAIN || "http://localhost:3000";
        const browser = await puppeteer.launch({ pipe: true });
        try {
            let page = await browser.newPage();
            const cookie = {
                domain: domain,
                name: "admin_cookie",
                value: key,
                httpOnly: true,
                secure: true,
                sameSite: 'Lax'
            };
            await page.setCookie(cookie);
            await page.goto(url);
            await sleep(30);
        } finally {
            await browser.close();
        }
    },
};
