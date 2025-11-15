import { chromium } from 'playwright';

async function astralTravel(url) {
    try {
        const browser = await chromium.launch();
        const page = await browser.newPage();

        await page.goto('http://127.0.0.1:3000/login');
        await page.getByRole('textbox', { name: 'Username' }).fill('metatron');
        await page.getByRole('textbox', { name: 'Password' }).fill(process.env.ADMIN_PASSWD || 'lordshiva42');
        await page.getByRole('button', { name: 'Log In' }).click();

        await page.goto(url);
        await page.getByRole('button', { name: 'Submit' }).click();
        const title = await page.title();
        await browser.close();
        return title;
    } catch (error) {
        console.log(error);
        return null;
    }
}

export { astralTravel };