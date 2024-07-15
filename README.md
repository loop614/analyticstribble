### Description
- Java Spring app with ElasticSearch
- Following user mouse input across web page
- Send data from frontend to Java Spring every x seconds
- Saving data in ElasticSearch

### Status
- Work in progress

### Requirements
- maven, java, npm, docker

### Quick Start
```console
$ npm install
$ ./node_modules/typescript/bin/tsc --watch
```
```console
$ docker compose up tribble_elastic
$ ./mvnw spring-boot:run
```
```console
$ docker compose up tribble_elastic tribble_kibana
$ ./mvnw spring-boot:run
```
