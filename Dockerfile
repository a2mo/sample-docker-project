FROM dockerrepo.tosanltd.com/docker/oracle-jdk:11-buster

ADD ./target/*-package.tar.gz /app/

RUN mv /app/*.jar /app/app.jar && chmod -R 777 /app

WORKDIR /app/

ENTRYPOINT ["java", "-jar", "app.jar"]