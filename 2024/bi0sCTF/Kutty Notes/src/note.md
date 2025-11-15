/login : (SQL Injection)
    - req.body.username: 
```javascript     
        validUser(username) {
            typeof username === "string" &&
            /^[a-z-A-Z-0-9-\.-\/]+$/.test(username) &&
            username.length <= 50
        }
```
    - req.body.password: 
        No filter 

/register : 
    - req.body.username: 
```javascript     
        validUser(username) {
            typeof username === "string" &&
            /^[a-z-A-Z-0-9-\.-\/]+$/.test(username) &&
            username.length <= 50
        }
        !username.includes("admin")
```        
    - req.body.password: 
        No filter 

/create : loggedIn
    - title (req.body.title): 
    - content (req.body.content):
```javascript      
        function checkContent(content) {
            return typeof content === "string" && !/meta|svg|math/gi.test(content);
        }
```
    - author (req.session.username): 
    
/posts : loggedIn
    - req.session.username

/post/:id : loggedIn
    - req.params.id:
    - author: req.session.username

/search : loggedIn 
    check parameter
    `^[a-zA-Z0-9\&\=\[\]\_]+$` 

    - query (req.query.q), filter (req.query.f): 
```javascript 
    const posts = await Post.query()
      .column(filter)
      .where("title", "LIKE", `%${query}%`)
      .andWhere("author", req.session.username);
```

/verify : loggedIn  
    - uid (req.body.id)
    - user (req.session.username)

```javascript 
    async function visit(uid, user, admin_pass) {
        const browser = await puppeteer.launch({
            executablePath: "/usr/bin/chromium-browser",
            args: ["--no-sandbox", "--disable-dev-shm-usage"],
        });
        try {
            const url = `http://localhost:8000/verify?uid=${uid}&user=${user}`;
            console.log(`Visiting ${url}`);
            const page = await browser.newPage();
            await page.goto("http://localhost:8000/login");
            await page.type('input[id="logusername"]', "admin");
            await page.type('input[id="logpassword"]', admin_pass);
            await page.click('button[id="log"]');
            await page.waitForSelector("#q");
            await page.goto(url, { waitUntil: "networkidle0", timeout: 60000 });
            await page.close();
        } catch (err) {
            console.log(`Error in visit: ${err}`);
        }
        try {
            await browser.close();
            console.log("Browser closed");
        } catch (e) {
            console.log("Closing browser failed.");
        }
    }
```

/delete : adminOnly
    - req.body.id (req.body.id):

/all : adminOnly 
    - req.query.page :
    - req.query.limit : 

/verify : adminOnly 
    - req.query.uid : 
    - req.query.user : 
    return post by uid

/:username/block : adminOnly
    - req.query.block : 
    - req.query.username : 