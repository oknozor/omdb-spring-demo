# Omdb Springboot Demo &emsp; [![Build Status]][travis] [![Quality Gate Status]][sonar] [![Coverage]][sonar]
                       
[Build Status]: https://travis-ci.com/oknozor/omdb-spring-demo.svg?branch=master
[travis]: https://travis-ci.com/oknozor/omdb-spring-demo
[Quality Gate Status]: https://sonarcloud.io/api/project_badges/measure?project=oknozor_omdb-spring-demo&metric=alert_status
[Coverage]: https://sonarcloud.io/api/project_badges/measure?project=oknozor_omdb-spring-demo&metric=coverage
[sonar]: https://sonarcloud.io/dashboard?id=oknozor_omdb-spring-demo

## Description 

This is a sample Springboot project using the [Open Movie Database](http://www.omdbapi.com/) API.

## Dependencies 

- Docker >= 19.03.11-1
- Java >= 14
- Postgresql >= 12.3

## Build & Run

To run the application you will need at least java OpenJdK 14, refer
to your specific OS to get it. 

1. build the application : `mvn package`
2. run : `java -jar target/omdbdemo-{version}.jar`
 
### Author 
   
Paul Delafosse
