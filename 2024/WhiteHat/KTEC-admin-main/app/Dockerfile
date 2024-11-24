FROM python:3.10-slim-buster

ENV DEBIAN_FRONTEND=noninteractive
ENV user=roronoa
ENV port=5000
ENV MYSQL_USER=guest
ENV MYSQL_PASSWORD=guest

ARG WORK_DIR=/app

WORKDIR ${WORK_DIR}

RUN apt-get update && \ 
    apt-get install -y python-dev build-essential default-libmysqlclient-dev pkg-config

COPY dist/ ${WORK_DIR}
RUN pip install -r requirements.txt

RUN adduser --disabled-password $user

RUN chown $user:root -R ${WORK_DIR} && \
    chmod 555 -R ${WORK_DIR}

EXPOSE $port
USER $user

CMD ["python", "__init__.py"]