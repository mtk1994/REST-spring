# REST-spring
This is a project with a few examples of simple REST usage in Spring Boot

Version of the used libraries/software:
- Java: 17.0.7
- Spring boot: 3.1.0
- Postgresql 10
- IntelliJ: 2023.1.2

Template of the project created using:
https://start.spring.io/

You can find Postman collection of example rest requests that can be used to test the application in the external_resources/postman directory.

You can run this application as stand-alone application like this:
- maven clean
- maven install
- in the console open 'target' folder 
- run command: java -jar demo-0.0.1-SNAPSHOT.jar --server.port=8081
It should run this application on the port 8081, so you should be able to run other application (e.g.: application that will test this application) on default 8080 port.
