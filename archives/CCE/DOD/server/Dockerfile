FROM php:7.4-apache

RUN apt-get update -y && apt-get install -y gcc
RUN docker-php-ext-install mysqli

COPY config/apache.conf /etc/apache2/apache2.conf

RUN mkdir /var/www/cgi-bin
RUN chmod 777 /var/www/cgi-bin

RUN find /etc/apache2/sites-enabled/* -exec sed -i 's/#*[Cc]ustom[Ll]og/#CustomLog/g' {} \;
RUN find /etc/apache2/sites-enabled/* -exec sed -i 's/#*[Ee]rror[Ll]og/#ErrorLog/g' {} \;

COPY server/source /var/www/html

RUN chown -R root:root /var/www/html
RUN chmod 775 /var/www/html
RUN mkdir /var/www/html/uploads
RUN chmod 777 /var/www/html/uploads

COPY flag.txt /flag.txt
COPY readflag.c /tmp/readflag.c

RUN chmod 440 /flag.txt
RUN gcc /tmp/readflag.c -o /readflag
RUN rm /tmp/readflag.c
RUN chmod 2555 /readflag

EXPOSE 80