#!/bin/bash
cd /home/user

while true; do
   node app.js;
   pkill firefox;
done
