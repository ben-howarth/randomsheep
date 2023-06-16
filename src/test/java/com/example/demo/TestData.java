package com.example.demo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestData {


   


    public static List<WeatherSensorReading> getTestWeatherDataList(List<Integer> sensorIds){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<WeatherSensorReading> testData = new ArrayList<WeatherSensorReading>();
        sensorIds.stream().forEach(sensorId -> {
           testData.addAll(
            List.of(
                new WeatherSensorReading(
                sensorId, 20.0, LocalDateTime.parse("2023-06-10 10:00:00", formatter), "HUMIDITY"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "HUMIDITY"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "HUMIDITY"
            ), new WeatherSensorReading(
                sensorId, 45.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "HUMIDITY"
            ), new WeatherSensorReading(
                sensorId, 20.0, LocalDateTime.parse("2023-06-10 10:00:00", formatter), "TEMPERATURE"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "TEMPERATURE"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "TEMPERATURE"
            ), new WeatherSensorReading(
                sensorId, 45.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "TEMPERATURE"
            ), new WeatherSensorReading(
                sensorId, 20.0, LocalDateTime.parse("2023-06-10 10:00:00", formatter), "UV_INDEX"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 45.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 20.0, LocalDateTime.parse("2023-06-10 10:00:00", formatter), "WIND_SPEED"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "WIND_SPEED"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "WIND_SPEED"
            ), new WeatherSensorReading(
                sensorId, 45.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "WIND_SPEED"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2022-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 0.0, LocalDateTime.parse("2022-06-15 10:00:00", formatter), "UV_INDEX"
            ), new WeatherSensorReading(
                sensorId, 20.0, LocalDateTime.parse("2022-06-10 10:00:00", formatter), "WIND_SPEED"
            ),  new WeatherSensorReading(
                sensorId, 25.0, LocalDateTime.parse("2022-06-15 10:00:00", formatter), "WIND_SPEED"
            ), new WeatherSensorReading(
                sensorId, 30.0, LocalDateTime.parse("2022-06-15 10:00:00", formatter), "WIND_SPEED"
            ), new WeatherSensorReading(
                sensorId, 45.0, LocalDateTime.parse("2023-06-15 10:00:00", formatter), "WIND_SPEED"
            )
            )
           );
        });
        
        return testData;
    } 
}
