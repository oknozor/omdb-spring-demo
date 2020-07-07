# Omdb Springboot Demo &emsp; ![Java CI with Maven](https://github.com/oknozor/omdb-spring-demo/workflows/Java%20CI%20with%20Maven/badge.svg) [![Quality Gate Status]][sonar] [![Coverage]][sonar]
                       
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
1. [record-architecture-decisions](docs/adr/0001-record-architecture-decisions.md)
1. [use-clean-architecture](docs/adr/0002-use-clean-architecture.md)

## Dependencies 

- Docker >= 19.03.11-1
- Java >= 14
- Postgresql >= 12.3

## Build & Run

The application requires that you have a running postgresql instance. 
You can configure it according to the [application.properties](src/main/resources/application.properties) config
or use the provided docker compose file : 

```$bash
cd src/main/docker
docker-compose up -d
```

You will also need at least java OpenJdK 14, please refer to your specific OS documentation to get it. 

1. build the application : `mvn package`
2. run : `java -jar target/omdbdemo-{version}.jar`
 
### Author 
   
Paul Delafosse

### Licence

This project is licenced under the [MIT License](LICENSE)
