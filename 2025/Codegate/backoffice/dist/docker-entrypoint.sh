#!/bin/sh

dockerize -wait tcp://backoffice-db:3306 -timeout 15s

cd /var/www/backoffice && \
    composer install && \
    composer dump-autoload && \
    php artisan config:clear && \
    php artisan migrate --force && \
    php artisan cache:clear && \
    php artisan optimize:clear && \
    php artisan config:cache

 apache2ctl -D FOREGROUND