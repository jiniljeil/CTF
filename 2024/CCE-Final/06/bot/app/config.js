const CONFIG = {
    app: {
        port: 3000,
        
        // whether to show bot error or not
        show_error: true,

        // TTL of session. unit in minutes
        session_alive: 60,
    }, 

    pow: {
        required: false,
        hash: 'sha1',

        // level of PoW. unit in .5 bytes
        level: 3,

        // size of salt(nonce). unit in .5 bytes
        salt_size: 3.5,

        // format of challenge. #S denotes salt, #A denotes answer
        chall_mask: "#S#A",

        // masking letter of answer
        answer_mask: "#",
    },

    bot: {
        // path to chrome. For linux: /usr/bin/google-chrome | For Mac: /Applications/Google Chrome.app/Contents/MacOS/Google Chrome
        //executablePath: "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
        executablePath: "/usr/bin/google-chrome",
        browserArgs: [
            "--incognito"
        ],

        // page.goto() timeout in ms
        load_timeout: 3000, 

        // timeout after page loaded
        page_hang: 15000,

        url_check: {
            scheme: process.env.BOT_URL_SCHEME || "http",
        }
    },

    challenge: {
        cookie_name: "",
        flag: "",
        domain: "52.231.191.39",
    }
}

Object.freeze(CONFIG);

module.exports = { CONFIG };