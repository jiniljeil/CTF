FROM python:latest

WORKDIR /app/

ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
ENV FLASK_RUN_PORT=1337
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
COPY ./webserver .
CMD ["flask", "run"]
