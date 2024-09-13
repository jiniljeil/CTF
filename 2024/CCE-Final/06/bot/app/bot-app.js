const express = require("express");
const uuid = require('uuid');
const pow = require('./pow');
const bot = require('./bot-impl');
const { CONFIG } = require('./config');

const app = express();
app.use(express.urlencoded({ extended: true }));

app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

var BOT_SESSIONS = {};
const SESSION_STATUS = {
    INIT: 0,
    RUNNING: 1,
    FINISHED: 2,
    ERROR: 3,
}

function getSession(sess_key) {
    if (!sess_key || typeof sess_key !== 'string')
        return null;
    if (!(sess_key in BOT_SESSIONS))
        return null;

    return BOT_SESSIONS[sess_key];
}

function clearSession() {
    BOT_SESSIONS = Object.fromEntries(Object.entries(BOT_SESSIONS).filter( ([key, sess]) => {
        return sess.created_at >= (Date.now() - CONFIG.app.session_alive * 60 * 1000)
    }));
}

app.get("/", (req, res) => {
    let new_key = uuid.v4();
    let new_pow = pow.getPow();

    clearSession();
    BOT_SESSIONS[new_key] = {
        status: SESSION_STATUS.INIT,
        pow: new_pow,
        created_at: Date.now()
    }

    // gen prefix
    let port = CONFIG.bot.url_check.port;
    if (port === "80" && CONFIG.bot.url_check.scheme == "http")
        port = "";
    
    if (port === "443" && CONFIG.bot.url_check.scheme == "https")
        port = "";

    let url_prefix = `${CONFIG.bot.url_check.scheme}://`
    if (port) {
        url_prefix += `:${port}`;
    }

    url_prefix += "/";
    
    res.render("index", { sess_key: new_key, pow: new_pow, prefix: url_prefix});
})

app.post("/request", (req, res) => {
    let { sess_key, pow_answer, url } = req.body;
    
    if (typeof sess_key !== "string" || (CONFIG.pow.required && typeof pow_answer !== "string") || typeof url !== "string" )
        return res.render("error-403", { msg: "Not allowed"});

    clearSession();
    let sess = getSession(sess_key);
    if (!sess)
        return res.render("error-403", { msg: "Invalid session"} );

    if (CONFIG.pow.required) {
        BOT_SESSIONS[sess_key].pow.checked = true;
        if (!pow.checkPow(pow_answer, sess.pow)) {
            return res.render("error-403", { msg: "Wrong PoW"} );
        }
    }
    
    BOT_SESSIONS[sess_key].status = SESSION_STATUS.RUNNING;
    BOT_SESSIONS[sess_key].url = url;

    bot.run(url)
       .then((res) => {
            BOT_SESSIONS[sess_key].status = SESSION_STATUS.FINISHED;
            BOT_SESSIONS[sess_key].done_at = res.time;
        })
        .catch((res) => {
            BOT_SESSIONS[sess_key].status = SESSION_STATUS.ERROR;
            BOT_SESSIONS[sess_key].done_at = res.time;

            if (CONFIG.app.show_error)
                BOT_SESSIONS[sess_key].error = res.msg;

        });

    res.send("<script>alert('제출 완료');history.back(-1);</script>");
})

app.listen(CONFIG.app.port, () => {
    console.log(`Bot is running at http://bot:${CONFIG.app.port}/`);
});