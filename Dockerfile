FROM novinrepo:8082/docker/oracle-jre:17.0.3.1-buster

ADD ./target/*-package.tar.gz /app/

RUN mv /app/*.jar /app/app.jar && chmod -R 777 /app

# multi stage docker image to reduce the final image size
FROM novinrepo:8082/docker/oracle-jre:17.0.3.1-buster

WORKDIR /app/

COPY --from=0 /app /app

CMD ["java", "-jar", "app.jar"]