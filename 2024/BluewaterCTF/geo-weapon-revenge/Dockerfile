FROM docker.osgeo.org/geoserver:2.26.x

ADD ./flag.txt /flag
ADD ./readflag /

RUN chown 0:0 /readflag /flag
RUN chmod 400 /flag
RUN chmod +x /readflag
RUN chmod +s /readflag

ADD ./update_creds.sh /opt/update_credentials.sh
RUN chmod 777 /opt/update_credentials.sh
ENTRYPOINT ["bash", "-c","/opt/startup.sh"]
