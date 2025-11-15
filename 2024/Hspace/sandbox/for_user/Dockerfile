FROM node:latest

WORKDIR /app
COPY ./src/index.js /app
COPY ./src/package.json /app
COPY ./flag.txt /
RUN npm install 
RUN chmod +x /app/index.js
RUN useradd -m app
USER app
ENV NODE_ENV=production
ENV PORT=8000
CMD ["/app/index.js"]