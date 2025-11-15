#!/bin/bash
cd /home/user

while true; do
   node app.js;
   pkill chrome;
done
