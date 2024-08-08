#!/bin/bash

service nginx start
service php8.2-fpm start

while true;
do
sleep 3600;
done