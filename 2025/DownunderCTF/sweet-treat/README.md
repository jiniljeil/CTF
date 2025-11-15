# Important information

Commands to get the challenge up locally:

```
docker build -t sweettreat .
docker run -v $(pwd)/webapp:/usr/local/tomcat/webapps/ROOT -v $(pwd)/directory.db:/opt/directory.db -p 8080:8080 sweettreat
```

The xssbot does not run locally, but I have provided a sample docker-compose.yml file if you want to simulate the challenge entirely, although its not needed to figure out the challenge itself.
