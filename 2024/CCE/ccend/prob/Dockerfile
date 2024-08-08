FROM --platform=linux/amd64 ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update
RUN apt-get install -y software-properties-common ca-certificates apt-transport-https wget
RUN add-apt-repository ppa:ondrej/php
RUN apt-get update

RUN apt-get install -y nginx php8.2-fpm php8.2-mysqli

RUN rm /etc/nginx/sites-enabled/default
COPY nginx.conf /etc/nginx/sites-enabled/nginx.conf

ADD run.sh /tmp/run.sh
ADD src/html /var/www/html

CMD ["/bin/bash", "/tmp/run.sh"]