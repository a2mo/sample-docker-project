# Docker Spring Hello World Sample Project

This is a very basic Spring Hello World application.

## Quick Start

Clone the repo down locally:

```console
git clone https://github.com/a2mo/sample-docker-project.git
cd sample-docker-project
```

Assuming you have Java and Maven installed you can run it with:

```console
$ mvn spring-boot:run
2022-07-31T15:37:40.811 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStarting@55:
    Starting SampleApplication using Java 11.0.14 on Mahi-PC with PID 26340 (E:\github\docker-sample\sample-docker-project\target\classes started by alial in E:\github\docker-sample\sample-docker-project) 
2022-07-31T15:37:40.813 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStartupProfileInfo@634:
    No active profile set, falling back to 1 default profile: "default" 
2022-07-31T15:37:42.769 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStarted@61:
    Started SampleApplication in 2.229 seconds (JVM running for 2.587) 
```

Point your web browser or use wget/curl/httpy at `localhost:8080`:

```console
$ http localhost:8080/helloWorld?name=AliAlimohammadi
HTTP/1.1 200
Content-Length: 17
Content-Type: text/plain;charset=UTF-8
Date: Wed, 27 Feb 2019 21:13:35 GMT

Hello AliAlimohammadi
```

## Build and Run

### With Maven

```console
$ mvn clean install
...
...
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ sample ---
[INFO] Installing E:\github\docker-sample\sample-docker-project\target\sample-0.0.1-SNAPSHOT.jar to C:\Users\alial\.m2\repository\ir\a2mo\sample\0.0.1-SNAPSHOT\sample-0.0.1-SNAPSHOT.jar
[INFO] Installing E:\github\docker-sample\sample-docker-project\pom.xml to C:\Users\alial\.m2\repository\ir\a2mo\sample\0.0.1-SNAPSHOT\sample-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.188 s
[INFO] Finished at: 2022-07-31T15:45:37+04:30
[INFO] ------------------------------------------------------------------------
```

Then you can run it like so:

```console
$ java -jar target/sample-0.0.1-SNAPSHOT.jar

  ____             _             ____                        _
 |  _ \  ___   ___| | _____ _ __/ ___|  __ _ _ __ ___  _ __ | | ___
 | | | |/ _ \ / __| |/ / _ \ '__\___ \ / _` | '_ ` _ \| '_ \| |/ _ \
 | |_| | (_) | (__|   <  __/ |   ___) | (_| | | | | | | |_) | |  __/
 |____/ \___/ \___|_|\_\___|_|  |____/ \__,_|_| |_| |_| .__/|_|\___|
                                                      |_|
                                     (v0.0.1-SNAPSHOT)

2022-07-31T15:47:07.635 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStarting@55:
    Starting SampleApplication v0.0.1-SNAPSHOT using Java 11.0.14 on Mahi-PC with PID 30168 (E:\github\docker-sample\sample-docker-project\target\sample-0.0.1-SNAPSHOT.jar started by alial in E:\github\docker-sample\sample-docker-project)
2022-07-31T15:47:07.638 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStartupProfileInfo@634:
    No active profile set, falling back to 1 default profile: "default"
2022-07-31T15:47:10.034 [INFO]  thread:[main] ir.a2mo.sample.SampleApplication.logStarted@61:
    Started SampleApplication in 2.799 seconds (JVM running for 3.296)

```

### With Docker

If you have Docker you can skip using Maven and Java and Build a docker image:

```console
$ docker build -t a2mo/sample-docker-project .
...
...
Step 9/10 : COPY --from=BUILD /target/$JAR /app.jar
 ---> 8dc7d3d7bde4
Step 10/10 : ENTRYPOINT ["java","-jar","/app.jar"]
 ---> Running in b96dbe5b88c0
Removing intermediate container b96dbe5b88c0
 ---> 5405805d6f47
Successfully built 5405805d6f47
Successfully tagged a2mo/sample-docker-project:latest
```

Run it:

```console
$ docker run --name sample-docker-project -d --rm -p 8080:8080 a2mo/sample-docker-project
6d47dc1ea4833f1a68c6969d4969a74a4d656b9a85600fd089b3cf0ca9716b9d

$ curl localhost:8080/helloworld?name=AliAlimohammadi
Hello AliAlimohammadi

$ docker rm -f sample-docker-project
```

Example for command-line arg:
To change the server port, you can pass it as command-line arg after the image name.

```console
$ docker run --name sample-docker-project -d --rm -p 8080:8687 a2mo/sample-docker-project --port=8687
6d47dc1ea4833f1a68c6969d4969a74a4d656b9a85600fd089b3cf0ca9716b9d

$ curl localhost:8080/helloworld?name=AliAlimohammadi
Hello AliAlimohammadi

$ docker rm -f sample-docker-project
```
other command-line args:
--context-path for change context-path
--author for change author's name

Of course for change server port,you can do it with pass environment variable 'SERVER_PORT'.

```console
$ docker run --name sample-docker-project -d --rm -e SERVER_PORT=8687 -p 8080:8687 a2mo/sample-docker-project
6d47dc1ea4833f1a68c6969d4969a74a4d656b9a85600fd089b3cf0ca9716b9d

$ curl localhost:8080/helloworld?name=AliAlimohammadi
Hello AliAlimohammadi

$ docker rm -f sample-docker-project
```
