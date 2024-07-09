FROM golang:latest

ENV GO111MODULE=on \
    CGO_ENABLED=0 \
    GOOS=linux \
    GOARCH=amd64

ENV DB_HOST=postgres-server \
    DB_NAME=db \
    DB_PASSWORD=codegate2024foruser \
    DB_PORT=5432 \
    DB_USER=codegate2024

COPY ./src /web

RUN groupadd -r user
RUN useradd user -g user 
RUN mkdir /home/user 
RUN chown -R user:user /home/user
RUN chown -R user:user /web

WORKDIR /web/codegate.module
USER user 
EXPOSE 7777

RUN go mod download
RUN go build -o ChatService .
  
ENTRYPOINT ["/web/codegate.module/ChatService"]