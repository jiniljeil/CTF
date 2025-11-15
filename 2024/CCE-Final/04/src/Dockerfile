FROM php:8.4.0alpha1-apache

# install rewrite module
RUN a2enmod rewrite

# enable mysqli extension
RUN docker-php-ext-install mysqli

COPY ./html /var/www/html/

RUN mkdir -p /app/user
RUN mkdir -p /app/report
COPY ./admin_profile /app/user/admin

RUN chown -R www-data:www-data /app

COPY 000-default.conf /etc/apache2/sites-available/000-default.conf

RUN apt-get update && apt-get install -y gcc
COPY ./flag /flag
RUN chmod 600 /flag && chown root:root /flag

COPY readflag.c /readflag.c
RUN RAND=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 8 | sed 1q) && \
    gcc /readflag.c -o /readflag-$RAND && \
    chmod +s /readflag-$RAND   

CMD ["apache2-foreground"]
