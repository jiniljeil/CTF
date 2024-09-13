FROM node:20.15.0

RUN apt-get update && apt-get install gnupg wget -y && \
  wget --quiet --output-document=- https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor > /etc/apt/trusted.gpg.d/google-archive.gpg && \
  sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' && \
  apt-get update && \
  apt-get install google-chrome-stable -y --no-install-recommends

COPY app /app
WORKDIR /app

RUN PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true npm install

RUN chmod +x run.sh
RUN useradd -ms /bin/bash user
USER user

ENTRYPOINT ["./run.sh"]
