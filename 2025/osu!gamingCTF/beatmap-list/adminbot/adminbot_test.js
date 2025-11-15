// script to emulate admin bot
const puppeteer = require("puppeteer");
const crypto = require("crypto");
const JSZip = require("jszip");
const fs = require("fs");

const FLAG = process.env.FLAG || "osu{test_flag}";
const SITE = "http://localhost";

const BASE_OSZ = fs.readFileSync("flag_base.osz");

const replaceFields = (osu, fields) => {
    const lines = osu.split("\n");
    for (const [key, value] of Object.entries(fields)) {
        const index = lines.findIndex((line) => line.startsWith(key + ":"));
        lines[index] = key + ":" + value;
    }
    return lines.join("\n");
};

const generateFlagOSZ = async (flag) => {
    const zip = new JSZip();
    const oszZip = await zip.loadAsync(BASE_OSZ);

    const beatmapSetId = crypto.randomInt(1000000, 9999999);

    for (const file of Object.keys(oszZip.files)) {
        if (file.endsWith(".osu")) {
            const osuFile = oszZip.file(file);
            const osu = await osuFile.async("string");
            const flagOsu = replaceFields(osu, {
                "Title": flag,
                "BeatmapSetID": beatmapSetId,
                "BeatmapID": crypto.randomInt(1000000, 9999999),
            });
            zip.file(file, flagOsu);
        }
    }

    return zip.generateAsync({ type: "nodebuffer" });
};

const visit = async (url) => {
    if (!fs.existsSync("flag.osz")) {
        const flagOSZ = await generateFlagOSZ(FLAG);
        fs.writeFileSync("flag.osz", flagOSZ);
    }

    let browser;
    try {
        browser = await puppeteer.launch({
            headless: "new",
            pipe: true,
            args: [
                "--no-sandbox",
                "--disable-setuid-sandbox",
                "--js-flags=--jitless",
            ],
            dumpio: true
        });

        const ctx = await browser.createBrowserContext();

        let page = await ctx.newPage();
        await page.goto(SITE, { timeout: 3000, waitUntil: 'networkidle0' });

        await page.evaluate(() => {
           document.querySelector(".upload-link").click();
        });

        const oszUpload = await page.waitForSelector("input[type='file']");
        await oszUpload.uploadFile("flag.osz");
        await page.evaluate(() => {
            document.querySelector("button.swal2-confirm").click();
        })

        await new Promise(r => setTimeout(r, 3000));

        await page.close();
        page = await ctx.newPage();

        await page.goto(url, { timeout: 3000, waitUntil: 'domcontentloaded' })
        await new Promise(r => setTimeout(r, 60_000));

        await browser.close();
        browser = null;
    } catch (err) {
        console.log(err);
    } finally {
        if (browser) await browser.close();
    }
};

visit("EXPLOIT_SITE");