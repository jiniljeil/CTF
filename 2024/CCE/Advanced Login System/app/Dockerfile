FROM php:7.4-apache

RUN apt update && apt install gcc
RUN docker-php-ext-install mysqli

RUN rm -rf /var/www/html/*

COPY flag.txt /flag.txt
COPY readflag.c /tmp/readflag.c

RUN chmod 440 /flag.txt
RUN gcc /tmp/readflag.c -o /readflag
RUN rm /tmp/readflag.c
RUN chmod 2555 /readflag

COPY src /var/www/html/

RUN ln -sf /dev/null /var/log/apache2/access.log && \
    ln -sf /dev/null /var/log/apache2/error.log

USER root

EXPOSE 80