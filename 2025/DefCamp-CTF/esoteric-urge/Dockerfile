FROM node:latest

RUN useradd -m ctf
RUN mkdir -p /home/ctf/app

WORKDIR /home/ctf/app

COPY app .

RUN npm install
RUN npm update
RUN npx playwright install chromium
RUN npx playwright install-deps


ENTRYPOINT ["npm", "run", "start"]

