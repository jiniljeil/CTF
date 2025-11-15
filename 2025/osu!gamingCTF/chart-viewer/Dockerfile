FROM gcc:latest AS build-readflag
COPY readflag.c /readflag.c
RUN gcc /readflag.c -o /readflag && \
    chown root:root /readflag && chmod 4755 /readflag

FROM node:latest

COPY --from=build-readflag /readflag /readflag
RUN chown root:root /readflag && chmod 4755 /readflag

COPY --chown=root:root flag.txt /flag.txt
RUN chmod 400 /flag.txt

# install 7z and unzip
RUN apt-get update && apt-get install -y p7zip-full unzip

RUN useradd -m app
USER app

WORKDIR /app
COPY package.json ./
RUN npm install
COPY public ./public
COPY index.js ./

ENTRYPOINT [ "node", "index.js" ]
