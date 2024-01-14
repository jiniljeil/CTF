#admin:showmetheenki1234!@#$

FROM debian:bullseye

RUN DEBIAN_FRONTEND=noninteractive apt-get update && \
    apt-get install -y \
        nginx \
        php-fpm \
        php-mysql \
    && rm -rf /var/lib/apt/lists/

RUN rm -rf /var/www/html/*
COPY config/default /etc/nginx/sites-enabled/default
COPY config/www.conf /etc/php/7.4/fpm/pool.d/www.conf

COPY flag.txt /

COPY src /var/www/html/

RUN chown -R root:root /var/www && \
    mkdir /var/www/html/uploads && \
    chmod 703 /var/www/html/uploads

RUN ln -sf /dev/stdout /var/log/nginx/access.log && \
    ln -sf /dev/stderr /var/log/nginx/error.log

USER root

EXPOSE 80
CMD while true; do find /var/www/html/uploads/ -mindepth 1 -mmin +15 -delete; sleep 60s; done & \
    /etc/init.d/php7.4-fpm start && \
    nginx -g 'daemon off;'