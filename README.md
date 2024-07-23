# Desafio Técnico Outsera - Awards Interval

## API Endpoints

| METHOD | URI                           |
| ------ | ----------------------------- |
| GET    | /api/producers                |
| GET    | /api/producers/{id}           |
| GET    | /api/producers/award-interval |

| GET | /api/movies |
| GET | /api/movies/{id} |

| GET | /api/studios |
| GET | /api/studios/{id} |

## Compile

```
	mvn package
```

## Execute

```
	mvn spring-boot:run
	curl http://localhost:8080/api/producers/award-interval
```

## CSV File to import

```
	path: src/main/resources/movielist.csv
```

## H2 Console

- URL = [http://localhost:8080/api/h2-console](http://localhost:8080/api/h2-console)
- DATABASE = jdbc:h2:mem:raspberry_awards
- USERNAME = sa
- PASSWORD = sa

## H2 Swagger

- URL = [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)
