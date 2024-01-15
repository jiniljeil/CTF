FROM python:latest

WORKDIR /app

COPY . /app

RUN pip install -r requirements.txt && rm requirements.txt

RUN useradd -m ctf

USER ctf

CMD python app.py