# OpenTelemetry-Sample-Application

## Running

1. `./start-otel-collector.sh`
1. `./start-mongodb.sh`
1. mvn clean package
1. mvn spring-boot:run
1. Open http://localhost:8080/test/helloWorld?message=Test (multiple times to generate metrics.)
1. Open http://localhost:8081/ to see metrics database and contents in mongodb.

Needs modified OTel SDK at https://github.com/FWinkler79/opentelemetry-java/tree/working-persistency-additions.

See also: 
* https://github.com/spring-projects/spring-framework/issues/31998#issuecomment-1884479117
* https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-6.1-Release-Notes#parameter-name-retention