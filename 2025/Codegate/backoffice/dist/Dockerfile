FROM php:8.4-apache
ENV DEBIAN_FRONTEND=noninteractive

ARG user
ARG uid

COPY ./flag /flag
RUN chmod 400 /flag && chown root:root /flag
COPY readflag.c /tmp/readflag.c
RUN RAND=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 8 | sed 1q) && \
    gcc /tmp/readflag.c -o /readflag-$RAND && \
    chmod +s /readflag-$RAND && \
    rm /tmp/readflag.c

RUN ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN apt-get update && apt-get install -y \
    libpng-dev \
    libjpeg-dev \
    libfreetype6-dev \
    libonig-dev \
    libxml2-dev \
    wget \
    git \
    net-tools \
    curl \
    zip \
    unzip

RUN docker-php-ext-configure gd && \
    docker-php-ext-install -j$(nproc) gd && \
    docker-php-ext-install pdo pdo_mysql mbstring

COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

RUN useradd -G www-data,root -u $uid -d /home/$user $user
RUN mkdir -p /home/$user/.composer && \
    chown -R $user:$user /home/$user

WORKDIR /var/www/$user

COPY ./src /var/www/$user

RUN cp -rp /etc/apache2/apache2.conf /etc/apache2/apache2.conf.bak
COPY apache/apache2.conf /etc/apache2/apache2.conf
RUN cp -rp /etc/apache2/conf-available/charset.conf /etc/apache2/conf-available/charset.conf.bak
COPY apache/charset.conf /etc/apache2/conf-available/charset.conf
RUN cp -rp /etc/apache2/conf-available/security.conf /etc/apache2/conf-available/security.conf.bak
COPY apache/security.conf /etc/apache2/conf-available/security.conf
RUN cp -rp /etc/apache2/sites-available/000-default.conf /etc/apache2/sites-available/000-default.conf.bak
COPY apache/000-default.conf /etc/apache2/sites-available/000-default.conf
COPY apache/php.ini-production /usr/local/etc/php/php.ini

RUN chown -R $user:$user /var/www/$user && \
    chmod -R 755 /var/www/$user

RUN mkdir -p /var/www/storage && \ 
    mkdir /var/www/storage/templates && \
    mkdir /var/www/storage/uploads && \
    chown -R $user:$user /var/www/storage

RUN mv /var/www/$user/public/templates/* /var/www/storage/templates && \
    rm -rf /var/www/$user/public/templates && \
    mv /var/www/$user/public/security_module.json /var/www/storage/security_module.json

RUN chmod 755 -R /var/www/storage

ENV DOCKERIZE_VERSION v0.2.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz
RUN rm /var/www/$user/dockerize-linux-amd64-v0.2.0.tar.gz

COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

RUN a2enmod rewrite && \  
    a2enmod headers

RUN chmod 700 /bin/mv && chmod 700 /usr/bin/curl && chmod 700 /bin/rm && chmod 700 /bin/echo && chmod 700 /bin/chmod && chmod 700 /bin/chown && chmod 700 /bin/sed && chmod 700 /usr/bin/awk && chmod 700 /usr/bin/perl && chmod 700 /usr/bin/tr && chmod 700 /usr/bin/tee && chmod 700 /bin/dd && chmod 700 /bin/cp

ENTRYPOINT [ "/docker-entrypoint.sh" ]
USER $user
EXPOSE 80