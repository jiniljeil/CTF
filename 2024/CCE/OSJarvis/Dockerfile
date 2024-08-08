FROM openjdk:17-jdk-alpine

RUN apk add --no-cache maven

COPY osjarvis-app /usr/src/pentestgo
WORKDIR /usr/src/pentestgo

RUN mvn clean package

FROM tomcat:9.0

RUN rm -rf /usr/local/tomcat/webapps/
RUN mv /usr/local/tomcat/webapps.dist/ /usr/local/tomcat/webapps/
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY --from=0 /usr/src/pentestgo/target/osjarvisweb-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
COPY ./tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY ./context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
COPY ./flag.txt /

RUN groupadd -r appuser && useradd --no-log-init -r -g appuser appuser
RUN chown -R appuser:appuser /usr/local/tomcat
USER appuser

EXPOSE 8080

CMD ["catalina.sh", "run"]