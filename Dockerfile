FROM dockerrepo.tosanltd.com/docker/oracle-jre:11-buster

ADD ./target/*-package.tar.gz /app/

RUN mv /app/*.jar /app/app.jar && chmod -R 777 /app

# multi stage docker image to reduce the final image size
FROM dockerrepo.tosanltd.com/docker/oracle-jre:11-buster

WORKDIR /app/

COPY --from=0 /app /app

CMD ["java", "-jar", "app.jar"]