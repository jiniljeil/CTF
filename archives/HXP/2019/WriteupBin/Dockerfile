# echo 'hxp{FLAG}' > flag.txt && < /dev/urandom tr -dc a-f0-9 | head -c 16 > writeup-id.txt && docker build -t writeupbin . && docker run --cap-add=SYS_ADMIN --security-opt apparmor=unconfined -ti -p 8001:80 writeupbin

FROM debian:buster

RUN apt-get update && \
    < /dev/urandom tr -dc _A-Z-a-z0-9 | head -c32 > /root/mysql-password.txt && \
    echo "mariadb-server mysql-server/root_password password $(cat /root/mysql-password.txt)" | debconf-set-selections && \
    echo "mariadb-server mysql-server/root_password_again password $(cat /root/mysql-password.txt)" | debconf-set-selections && \
    apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        nginx \
        mariadb-server \
        php-fpm \
        php-mysql \
        python3-selenium \
        python3-pyvirtualdisplay \
        chromium-driver \
        xauth \
        xvfb \
    && rm -rf /var/lib/apt/lists/

COPY ynetd /sbin/
COPY docker-stuff/default /etc/nginx/sites-enabled/default
COPY docker-stuff/www.conf /etc/php/7.3/fpm/pool.d/www.conf


#  # Permission
#  7 rwx
#  6 rw-
#  5 r-x
#  4 r--
#  3 -wx
#  2 -w-
#  1 --x
#  0 ---


RUN useradd --create-home --shell /bin/bash ctf
WORKDIR /home/ctf
COPY admin.py /home/ctf


RUN chmod 555 /home/ctf && \
    chown -R root:root /home/ctf && \
    chmod -R 000 /home/ctf/* && \
    chmod 500 /sbin/ynetd && \
    chmod 444 /etc/nginx/sites-enabled/default /etc/php/7.3/fpm/pool.d/www.conf && \
    chmod 005 /home/ctf/admin.py


RUN rm -rf /var/www/html/*
ADD www /var/www/
RUN chown -R root:root /var/www && \
    find /var/www -type d -exec chmod 555 {} \; && \
    find /var/www -type f -exec chmod 444 {} \;

RUN ln -sf /dev/stdout /var/log/nginx/access.log && \
    ln -sf /dev/stderr /var/log/nginx/error.log


# setup mariadb
COPY db.sql writeup-id.txt flag.txt /root/
RUN mysqld_safe & while ! mysqladmin --silent ping; do sleep 0.1; done && \
    replace '__FLAG__' "$(cat /root/flag.txt)" -- /root/db.sql && \
    replace '__DB_PASSWORD__' "$(< /dev/urandom tr -dc A-Za-z0-9 | head -c32)" -- /root/db.sql /var/www/general.php && \
    replace '__WRITEUP_ID__' "$(cat /root/writeup-id.txt)" -- /root/db.sql /var/www/html/admin.php && \
    < /dev/urandom tr -dc A-Za-z0-9 | head -c32 > /root/admin-token.txt && \
    replace '__ADMIN_TOKEN__' "$(cat /root/admin-token.txt)" -- /home/ctf/admin.py && \
    replace '__ADMIN_HASH__' "$(php -r 'echo password_hash($argv[1], PASSWORD_DEFAULT);' -- $(cat /root/admin-token.txt))" -- /var/www/html/login_admin.php && \
    mysql -u root -p$(cat </root/mysql-password.txt) < /root/db.sql && \
    rm /root/*

USER www-data
RUN (find --version && id --version && sed --version && grep --version) > /dev/null
RUN ! find / -writable -or -user $(id -un) -or -group $(id -Gn|sed -e 's/ / -or -group  /g') 2> /dev/null | grep -Ev -m 1 '^(/dev/|/run/|/proc/|/sys/|/tmp|/var/tmp|/var/lock|/var/lib/php/sessions|/var/log/nginx/error.log|/var/log/nginx/access.log|/lib/systemd/system/)'

USER ctf
RUN ! find / -writable -or -user $(id -un) -or -group $(id -Gn|sed -e 's/ / -or -group  /g') 2> /dev/null | grep -Ev -m 1 '^(/dev/|/run/|/proc/|/sys/|/tmp|/var/tmp|/var/lock|/var/lib/php/sessions|/var/log/nginx/error.log|/var/log/nginx/access.log|/lib/systemd/system/)'
USER root

EXPOSE 80
CMD ynetd -lm -1 -lt 5 -t 60 -lpid 256 -sh n /home/ctf/admin.py & \
    /etc/init.d/php7.3-fpm start && \
    mysqld_safe &  while ! mysqladmin --silent ping; do sleep 0.1; done && \
    while true; do sleep 2m; echo "DELETE FROM writeup WHERE user_id <> 'admin' AND created_at < (NOW() - INTERVAL 20 MINUTE)" | mysql writeupbin; done & \
    nginx -g 'daemon off;'
