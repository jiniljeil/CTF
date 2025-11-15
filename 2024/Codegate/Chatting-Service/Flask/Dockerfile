FROM python:3.10

copy ./src /internal
copy ./entrypoint.sh /internal 

WORKDIR /internal

RUN apt-get update
RUN apt-get install -y gcc default-libmysqlclient-dev pkg-config net-tools
RUN apt-get install -y telnet python3-memcache
RUN apt-get install -y memcached libmemcached-tools
RUN python3 -m pip install -r requirements.txt
RUN groupadd -r internal && useradd internal -g internal && mkdir /home/internal && chown -R internal:internal /home/internal
RUN apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN chown -R internal:internal /internal
RUN chmod +x /internal/terminal
RUN chmod +x /internal/entrypoint.sh

EXPOSE 5000
EXPOSE 11211
ENTRYPOINT ["./entrypoint.sh"]