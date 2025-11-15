FROM perl:latest

COPY ./mythos /home/mythos
WORKDIR /home/mythos
RUN cpan -T Dancer2 JSON File::Slurp

COPY start.sh /home/mythos
RUN chmod +x /home/mythos/start.sh

CMD /home/mythos/start.sh