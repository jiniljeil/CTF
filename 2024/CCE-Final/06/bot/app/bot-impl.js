const puppeteer = require("puppeteer-core");
const { CONFIG } = require("./config");

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


function checkUrl(url) {
    // Implement: Valiadate & Sanitize URL if needs
    // Return final URL that bot will visit if it's okay, else empty string
    try {
        const parsed_url = new URL(url);

        if (!["http:","https:"].includes(parsed_url.protocol))
            return "";
    }
    catch {
        return "";
    }

    return url;
}

function now() {
    let dt = new Date();
    let local_dt = new Date(dt.getTime() - dt.getTimezoneOffset() * 60 * 1000);

    return local_dt.toISOString().replace("T", " ").slice(0, -5);
}

async function visit(url) {
    let browser, page;
    try {
        browser = await puppeteer.launch({
            executablePath: CONFIG.bot.executablePath,
            headless: true,
            args: CONFIG.bot.browserArgs
        }).catch(err => {
            err.knownMsg = "Error while launching browser";
            throw err;
        });
        page = await browser.newPage();

        await page.goto(`http://${CONFIG.challenge.domain}/admin/login.php`);
        await page.waitForSelector('body')
        await page.focus('#memberid')

        await page.keyboard.type("admin", { delay: 10 });

        await page.focus('#password')
        await page.keyboard.type("[REDACTED]", { delay: 10 });

        await new Promise(resolve => setTimeout(resolve, 500));
        await page.evaluate(()=>{login()});
        await new Promise(resolve => setTimeout(resolve, 500));

        await page.goto(url, { timeout: CONFIG.bot.load_timeout, waitUntil: "domcontentloaded" })
                .catch(err => {
                    err.knownMsg = "Error while visiting page";
                    throw err;
                });
        
        await sleep(CONFIG.bot.page_hang);
        
        await page.close();
        await browser.close();
    }
    catch (err) {
        try { 
            await browser?.close();
            await page?.close();
        } catch {} 

        if (err.knownMsg) 
            return { status: -1, msg: err.knownMsg };
        else
            return { status: -1, msg: "Unexpected error" };
    }

    return { status: 0 }
}


function run(url) {
    return new Promise(function(resolve, reject) {
        let final_url = checkUrl(url);
        if (!final_url)
            return reject({msg: "Invalid URL", time: now() });

        visit(url).then((result) => { 
            if (result.status == 0)
                return resolve({ time: now() });
            else
                return reject({ msg: result.msg, time: now() });
        });
    });
}


module.exports = { run };