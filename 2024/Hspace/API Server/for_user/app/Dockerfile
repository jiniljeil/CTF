FROM node:18

RUN mkdir /app

COPY app.js /app
COPY package.json /app

COPY flag.txt /flag.txt

WORKDIR /app
RUN npm install

RUN useradd -ms /bin/bash user
USER user

ENV NODE_ENV production

ENTRYPOINT ["node","app.js"]
