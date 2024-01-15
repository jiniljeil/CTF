const express = require("express");
const session = require("express-session");
const crypto = require("crypto");
const random_bytes = size => crypto.randomBytes(size).toString('hex');

const app = express();
const envrionment = ( process.env.NODE_ENV && ( process.env.NODE_ENV ).trim().toLowerCase() !== 'production' ) ? 'development' : 'production';
const env = Object.assign({}, process.env);
Object.freeze(env);

app.use(
    session({
        cookie: { maxAge : 600000 },
        secret: random_bytes(64),
    })
);

app.use((req, res, next) => {
    const _end = res.end;
    res.end = function(...args) {
        recover();
        _end.apply(this, args);
    };
    res.setHeader("connection", "close");
    next();
});

app.use(express.urlencoded({ extended: false }));
app.use(express.json());

const users = new Map([
    [],
]);

users.set("admin", random_bytes(64));

const saved = Object.create(null);
for (let v of Object.getOwnPropertyNames(Object.prototype)) {
    saved[v] = Object.prototype[v];
};

Object.freeze(saved);

const recover = _=>{
    for(let v of Object.getOwnPropertyNames(Object.prototype)){
        if(v in saved){
            Object.prototype[v] = saved[v]
        } else {
            delete Object.prototype[v]
        }
    }
};

const UNSAFE_KEYS = ["__proto__", "constructor", "prototype"];

const merge = (obj1, obj2) => {
  for (let key of Object.keys(obj2)) {
    if (UNSAFE_KEYS.includes(key)) continue;
    key = key.trim();
    const val = obj2[key];
    if (typeof obj1[key] !== "undefined" && typeof val === "object") {
      obj1[key] = merge(obj1[key], val);
    } else {
        if (typeof val == "string" && val.startsWith("Function")) {
            obj1[key] = Function(val.slice(8));
        } else {
            obj1[key] = val;
        }
    }
  }
  return obj1;
};

const main_template = `<html><head><title>API SERVER</title></head><body><div>This is an <h2 style="display: inline">API server</h2> for user management.</div> <br>Please refer to the documentation if you want to use this API.</body></html>`

app.get("/", (req, res) => {
    res.setHeader("Content-Type","text/html").send(main_template);
});

app.post("/api/register", (req, res) => {
    if (req.session.isLogin) {
        res.json({"status":false, "msg":"You are already logged in."});
    } else if (envrionment !== "development") {
        res.json({"status":false, "msg":"Registration is currently disabled in the production environment."});
    } else if (users.has(username)) {
        res.json({"status":false, "msg":"User already exists."});
    } else {
        users.set(req.body.username, sha256(req.body.password));
        res.json({"status":true, "msg":"Registration successful."});
    }
});

app.post("/api/login", (req, res) => {
    if (req.session.isLogin) {
        res.json({"status":false, "msg":"You are already logged in."});
    } else if (typeof username === 'string' && typeof password === 'string'){
        const { username, password } = req.body;
        if (users.has(username)) {
            if (users.get(username) === sha256(password)) {
                req.session.isLogin = true;
                res.json({"status":true, "msg":"Login success."});
            } else {
                res.json({"status":false, "msg":"Invalid Password."});
            }
        } else {
            res.json({"status":false, "msg":"User doesn't exist."});
        }
    } else {
        res.json({"status":false, "msg":"Invalid data"});
    }
});

app.post("/api/serverInfo", (req, res) => {
    const reqFilter = req.body;
    const filter = {};
    merge(filter, reqFilter);
    const result = {}
    if (Object.keys(filter).length == 0) {
        res.json(env);
    } else {
        for (let key of Object.keys(filter)) {
            if (env[key] !== undefined) {
                result[key] = env[key];
            }
        }
        res.json(result);
    }
});


app.listen(8080, () => {
  console.log(`Listening on http://0.0.0.0:8080`);
});
