FROM ubuntu:22.04

RUN apt update -y && apt install -y swi-prolog
WORKDIR /app
COPY server.pl .

EXPOSE 80
CMD swipl -l server.pl -g 'server(80)'