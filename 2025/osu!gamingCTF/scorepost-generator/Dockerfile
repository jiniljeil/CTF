# HMMMMMMMMMMMMMMMMMMMMMMMMM......
FROM vulhub/imagemagick:7.1.0-49
# what an interesting image to use...

RUN wget -qO- https://deb.nodesource.com/setup_22.x | bash -
RUN apt-get install -y nodejs

COPY . /app
WORKDIR /app

RUN npm install

RUN useradd -m user
USER user

COPY flag.txt /flag.txt

CMD ["node", "app.js"]