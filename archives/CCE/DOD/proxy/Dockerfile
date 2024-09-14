FROM node:latest

WORKDIR /app
COPY proxy/src/index.js /app
COPY proxy/src/package.json /app
RUN npm install 
RUN chmod +x /app/index.js
RUN useradd -m app
USER app
ENV NODE_ENV=production
ENV PORT=8000
CMD ["/app/index.js"]