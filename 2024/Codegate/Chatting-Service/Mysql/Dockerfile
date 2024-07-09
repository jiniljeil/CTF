FROM mysql:latest 

RUN mkdir /var/lib/mysql-no-volume

# /etc/mysql/my.cnf
COPY ./src/my.cnf /etc/mysql/my.cnf

CMD ["--datadir", "/var/lib/mysql-no-volume"]
