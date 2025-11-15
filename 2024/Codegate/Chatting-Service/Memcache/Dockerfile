FROM ubuntu:latest

RUN apt-get update \
    && apt-get install -y telnet memcached libmemcached-tools net-tools \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY ./src/memcached.conf /etc/memcached.conf

RUN service memcached restart
EXPOSE 11211
