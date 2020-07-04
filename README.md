# Omdb Springboot Demo &emsp; [![Build Status]][travis] [![Quality Gate Status]][sonar] [![Coverage]][sonar]
                       
[Build Status]: https://travis-ci.com/oknozor/omdb-spring-demo.svg?branch=master
[travis]: https://travis-ci.com/oknozor/omdb-spring-demo
[Quality Gate Status]: https://sonarcloud.io/api/project_badges/measure?project=oknozor_omdb-spring-demo&metric=alert_status
[Coverage]: https://sonarcloud.io/api/project_badges/measure?project=oknozor_omdb-spring-demo&metric=coverage
[sonar]: https://sonarcloud.io/dashboard?id=oknozor_omdb-spring-demo

## Description 

This is a sample Springboot project using the [Open Movie Database](http://www.omdbapi.com/) API.

## Contributing guidelines

If you want to participate please respect the following conventions : 
- We use [the conventional commit specification](https://www.conventionalcommits.org/en/v1.0.0/).
- We build our app with [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) in mind. 
- Any code addition shall not decrease the current coverage, please write tests ! 

## Documentation 

### Rest doc

Documentation for this API can be found [here](https://oknozor.github.io/omdb-spring-demo/) 

### Architectural Decision

We document our architectural decision throught the [ADR specification](https://adr.github.io/)

#### Records
1. [record-architecture-decisions](0001-record-architecture-decisions.md)
1. [use-clean-architecture.md](0002-use-clean-architecture.md)

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
