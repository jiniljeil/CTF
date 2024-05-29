import { $, escapeHTML } from "bun";
import qs from "qs";

const port = process.env.PORT || 3000;
const logFile = process.env.LOGFILE || ".log";

const server = Bun.serve({
    host: "0.0.0.0",
    port: port,
    async fetch(req) {
        const url = new URL(req.url);
        if (url.pathname === "/chall") {
            const params = qs.parse(url.search, { ignoreQueryPrefix: true });
            if (params.url.length < escapeHTML(params.url).length) {    // dislike suspicious chars
                return new Response("sorry, but the given URL is too complex for me");
            }

            const lyURL = new URL(params.url, "https://www.lycorp.co.jp");
            if (lyURL.origin !== "https://www.lycorp.co.jp") {
                return new Response("don't you know us?");
            }

            const rawFetched = await $`curl -sL ${lyURL}`.text();
            const counts = {
                "L": [...rawFetched.matchAll(/LINE/g)].length,
                "Y": [...rawFetched.matchAll(/Yahoo!/g)].length,
            }
            await $`echo $(date '+%Y-%m-%dT%H:%M:%S%z') - ${params.url} ::: ${JSON.stringify(counts)} >> ${logFile}`;

            const highlighted = escapeHTML(rawFetched)
                .replace(/LINE/g, "<mark style='color: #06C755'>$&</mark>")
                .replace(/Yahoo!/g, "<mark style='color: #FF0033'>$&</mark>");
            const html = `
                <h1>Your score is... 🐐<${counts.L + counts.Y}</h1>
                <details open>
                    <summary>Result</summary>
                    <blockquote>${highlighted}</blockquote>            
                </details>
            `;
            return new Response(html, { headers: { "Content-Type": "text/html; charset=utf-8" } });
        } else {
            return new Response("🎶😺≡≡≡😺🎶 Happy Happy Happy~")
        }
    }
});

console.log(`😺 on http://localhost:${server.port}`);