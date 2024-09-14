# Copy From https://github.com/sajjadium/ctf-archives/blob/main/HXP/2021/web/counter/Dockerfile

FROM debian:bullseye

RUN DEBIAN_FRONTEND=noninteractive apt-get update && \
    apt-get install -y \
        nginx \
        php-fpm \
        libzip-dev \
        php-zip \
    && rm -rf /var/lib/apt/lists/

RUN rm -rf /var/www/html/*
COPY config/default /etc/nginx/sites-enabled/default
COPY config/www.conf /etc/php/7.4/fpm/pool.d/www.conf

COPY flag.txt config/readflag /
RUN chown 0:1337 /flag.txt /readflag && \
    chmod 040 /flag.txt && \
    chmod 2555 /readflag

COPY src /var/www/html/

RUN ln -sf /dev/stdout /var/log/nginx/access.log && \
    ln -sf /dev/stderr /var/log/nginx/error.log

RUN find / -ignore_readdir_race -type f \( -perm -4000 -o -perm -2000 \) -not -wholename /readflag -delete
USER www-data
RUN (find --version && id --version && sed --version && grep --version) > /dev/null
USER root

EXPOSE 80
CMD /etc/init.d/php7.4-fpm start && \
    nginx -g 'daemon off;'