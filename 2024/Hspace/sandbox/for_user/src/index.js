#!/usr/bin/env node
const express = require('express')
const cookieParser = require('cookie-parser')
const bodyParser = require('body-parser')
const fs = require('fs')
const crypto = require('crypto')
const vm2 = require('vm2')

const app = express()
const hashPasswd = p => { return crypto.createHash('sha256').update(p).digest('hex') }
const rand = _ => { return crypto.randomBytes(Math.ceil(0x32/2)).toString('hex').slice(0,0x32) }
const now = () => { return Math.floor(+new Date()/1000) }
const checkoutTimes = new Map()
const users = new Set()
var lastUid = 0

app.use(cookieParser())
app.use(bodyParser.json())

app.use((req,res,next) => {
	req.userUid = -1
	req.userData = ""

	let data = req.cookies.data
	let uid = req.cookies.uid
	let passwd = req.cookies.passwd

	if(uid == undefined || passwd == undefined)
		return next()

	let found = false
	for(let e of users.entries())
		if(e[0].uid == uid && e[0].password == passwd)
			found = true

	if(found){
		req.userUid = uid
		req.userData = data
	}

	next()
})

app.get('/',(req,res) => {
	res.type('text/plain').send("hack me ( ﾟ▽ﾟ)/ :)")
})

app.post('/login',(req,res) => {
	const {username, password }= req.body

	if(!username || !password)
		return res.json({ error: true, msg: "Bad params :(" })

	let u = null
	for(let e of users.entries())
		if(e[0].username == username)
			u = e[0]

	if(!u)
		return res.json({ error: true, msg: "User not found" })

	let hashedPassword = hashPasswd(password.toString().slice(-0x20))
	if(u.password != hashedPassword)
		return res.json({ error: true, msg: "Wrong password :(" })

	res.cookie('uid',u.uid)
	res.cookie('passwd',hashedPassword)
	res.json({ error: false, msg: "Logged in" })
})

app.post('/register',(req,res) => {
	const { username, password }= req.body
	if(!username || !password)
		return res.json({ error: true, msg: "Bad params" })

	for(let e of users.entries())
		if(e[0].username == username)
			return res.json({ error: true, msg: "Username exists" })

	let hashedPassword = hashPasswd(password.toString().slice(-0x20))
	let uid = lastUid++
	
	users.add({
		username: username.toString().slice(-0x20),
		password: hashedPassword,
		uid: uid
	})

	res.cookie('uid',uid)
	res.cookie('passwd',hashedPassword)
	res.json({ error: false, msg: "Registered" })
})

app.get('/checkout',(req,res) => {
	if(req.userUid == -1 || !req.userData)
		return res.json({ error: true, msg: "Login first" })

	if(parseInt(req.userUid) != 0)
		return res.json({ error: true, msg: "You can't do this sorry" })

	if(req.userData.length > 160)
		return res.json({ error: true, msg: "Too long!!" })

	if(checkoutTimes.has(req.ip) && checkoutTimes.get(req.ip)+1 > now()) {
		return res.json({ error: true, msg: 'too fast'})
	}
	checkoutTimes.set(req.ip,now())
	
	let sbx = {
		readFile: (path) => {
			if(!(new String(path).toString()).includes('flag'))
				return fs.readFileSync(path,{encoding: "utf-8"})
			return null
		},
		sum: (args) => args.reduce((a,b)=>a+b),
	}

	let vm = new vm2.VM({
		timeout: 20,
	    sandbox: sbx,
	    fixAsync: true,
	    eval: false
	})

	let result = ":(";
	try {
		result = new String(vm.run(`sum([${req.userData}])`))
	} catch (e) {}
	res.type('text/plain').send(result)
})

app.listen(8000,()=>console.log('Listening on 8000...'))
users.add({ username: "admin", password: hashPasswd(rand()), uid: lastUid++ })
