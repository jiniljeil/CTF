FROM python:3.11

LABEL author="arrester"

WORKDIR /app
COPY ./internal-inspection /app/
COPY ./flag /flag
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r /app/requirements.txt

RUN apt-get update

CMD ["python", "-u", "app.py"]