#!/usr/bin/env node

const express = require('express');
const httpProxy = require('http-proxy');
const proxy = httpProxy.createProxyServer();

const app = express();

const forbidden_path = ["cgi-bin", "uploads"];

app.use((req, res, next) => {
    forbidden_path.forEach((element) => {
        if (req.path.includes(element)) {
            res.status(403).send("<h1>Forbidden path</h1>")
        }
    })
    next();
});

app.get('/*', function (req, res) {
    proxy.web(req, res, { target: 'http://web' });
});

app.post('/*', function (req, res) {
    proxy.web(req, res, { target: 'http://web' });
});

app.listen(80, function () {
    console.log("start");
});