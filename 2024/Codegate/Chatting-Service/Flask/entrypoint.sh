#!/bin/bash 
service memcached start
/internal/terminal & 
python3 /internal/app.py