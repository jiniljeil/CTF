FROM node:20.5.1

RUN apt update && apt install -y supervisor nginx gcc

RUN mkdir -p /app
WORKDIR /app

COPY src .

RUN npm install

COPY config/supervisord.conf /etc/supervisord.conf
COPY config/nginx.conf /etc/nginx/nginx.conf

COPY flag.txt /flag.txt
RUN chmod 400 /flag.txt

COPY readflag.c /tmp/readflag.c

RUN gcc /tmp/readflag.c -o /readflag && rm /tmp/readflag.c
RUN chmod 2555 /readflag

EXPOSE 80

CMD ["/usr/bin/supervisord", "-c", "/etc/supervisord.conf"]