# Getting Started
This approach uses a h2 in-memory DB. 


I used spring JPA and jakarta for data persistence and retrieval and tried to follow a standard Layer based project structure.

The only metrics that are available are defined in `com.example.demo.domain.MetricType` 
they are HUMIDITY, TEMPERATURE, WIND_SPEED and UV_INDEX.
The only statistics that are available are defined in `com.example.demo.domain.StatisticType` 
they are AVERAGE, MAX, MIN and SUM.

I initially created both a weatherSensor table and a WeatherSensorReading table and their related entities. In the end for the scope of the problem I felt that a single table made more sense but there may still be some hangovers from the two table decision (though I think I've removed them all for the most part). 

## how to run
navigate to the root folder and run `./mvnw spring-boot:run` to start the server

when the app is up and running you can check against the data stored in the app on start up by using this command 

```
curl --location --request GET 'http://localhost:8080/getStatistics' \
--header 'Content-Type: application/json' \
--data '{
    "ids": [1],
    "dateRange": 1,
    "statistic": "SUM",
    "metrics": ["TEMPERATURE", "HUMIDITY"]
}'
```
And you can create new readings by using 

```
curl --location 'http://localhost:8080/weatherSensorReading' \
--header 'Content-Type: application/json' \
--data '{
    "sensorId": 100,
    "readingValue": 30.0,
    "metric": "TEMPERATURE"
}'
```

to run the tests navigate to the root folder and run `./mvnw test` for unit tests and `./mvnw integration-test` for integration tests




