## for local running
- please set the following value in /etc/hosts
```
127.0.0.1 hhhhhhhref
```

- docker build
```
$ export NEXTAUTH_URL="http://hhhhhhhref:3000"
$ export JWT_SECRET="<RANDOM_VALUE>"
$ export ADMIN_SECRET_TOKEN="<RANDOM_VALUE>"
$ export FLAG="LINECTF{.....}"
$ docker compose up -d
```

- then, http://hhhhhhhref:3000/
- 
