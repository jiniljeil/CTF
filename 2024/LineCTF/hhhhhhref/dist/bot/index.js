const Redis = require("ioredis");
const { default: puppeteer } = require("puppeteer");
const dotenv = require('dotenv')
dotenv.config()

console.log(process.env.REDISBOT_PORT, process.env.REDISBOT_HOST);
const redis = new Redis(process.env.REDISBOT_PORT || 6379, process.env.REDISBOT_HOST || "redisbot");

const delay = async (ms) => new Promise(resolve => setTimeout(resolve, ms));

const crawl = async (username, password, code) => { 
    let browser = undefined;
    let page = undefined;
    try {
        const access = async () => {
            console.log("[!] Starting to crawl!");
            browser = await puppeteer.launch({
                executablePath: process.env.PUPPETEER_EXECUTABLE_PATH,
                headless: "new",
                product: "chrome",
                ignoreHTTPSErrors: true,
                args: [
                    "--no-sandbox",
                    "--disable-background-networking",
                    "--disk-cache-dir=/dev/null",
                    "--disable-default-apps",
                    "--disable-extensions",
                    "--disable-desktop-notifications",
                    "--disable-gpu",
                    "--disable-popup-blocking",
                    "--disable-sync",
                    "--disable-translate",
                    "--disable-dev-shm-usage",
                    "--hide-scrollbars",
                    "--metrics-recording-only",
                    "--mute-audio",
                    "--no-first-run",
                    "--safebrowsing-disable-auto-update",
                    "--no-zygote",
                ],
            });
            page = await browser.newPage();
    
            // login
            await page.goto(`${process.env.NEXTAUTH_URL}/api/auth/signin?callbackUrl=/`);
            await page.type("#input-name-for-credentials-provider", username);
            await page.type("#input-password-for-credentials-provider", password);
    
            await Promise.all([
                page.waitForNavigation(),
                page.click("button[type=submit]"),
            ]);
    
            // crawl with provided code
            await page.setExtraHTTPHeaders({
                "X-LINECTF-FLAG": process.env.FLAG
            });
            await page.goto(`${process.env.NEXTAUTH_URL}/rdr?errorCode=${code}`);
            await delay(1500);

            console.log("[*] Crawled!");
        }

        await Promise.race([
            access(), 
            delay(7 * 1000) // with 7 sec timeout limitation
        ]);

    } catch (error) {
        console.log("[!] Error");
        console.log(error);
    } finally {
        if (page !== undefined) {
            await page.close();
        }
        if (browser !== undefined) {
            await browser.close();
        }
        console.log("[+] Done!");
    }
}

const callback = async (err, result) => {
    const username = result[1].split("-")[1];
    const password = result[1].split("-")[2];
    const code = result[1].split("-")[3];
    await crawl(username, password, code);
}

const handler = async () => {
    console.log("[+] Starting...");
    while(true) {
        await redis.blpop("crawl_url", 0, callback);
        await delay(1500); // Zzz....
    }
}

handler();
