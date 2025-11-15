#!/bin/bash

while true; do
  cd /home/mythos && plackup -p 1338 bin/app.psgi
done
