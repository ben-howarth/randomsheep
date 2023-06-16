package com.example.demo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class WeatherResource {

    @Autowired
    WeatherSensorService weatherSensorService;

    @RequestMapping(path = "/weatherSensor/{id}", method = RequestMethod.GET )
    public WeatherSensor getWeatherSensorById(
        @PathVariable("id") Integer id) {
        return weatherSensorService.getWeatherSensorById(id);
    }

    @RequestMapping(path = "/getStatistics", method = RequestMethod.GET )
    public Double getStatisticForWeatherSensor(
        @RequestParam("id") List<Integer> id,
        @RequestParam(name = "startDate") Timestamp startDate,
        @RequestParam(name = "endDate") Timestamp endDate,
        @RequestParam(name = "statistic") String statistic
        ) {
        StatisticType statisticType = StatisticType.valueOf(statistic);
        return weatherSensorService.getStatisticsForWeatherSensor(ids, startDate, endDate, statisticType);
    }


    @RequestMapping(path = "/weatherSensor", method = RequestMethod.POST )
    public WeatherSensor createNewSensor(
        @RequestParam(name = "sensorType") String sensorType
    ) {
        WeatherSensor weatherSensor = new WeatherSensor(sensorType);
        return weatherSensorService.createNewSensor(weatherSensor);
    }

    @RequestMapping(path = "/weatherSensorReading", method = RequestMethod.POST )
    public WeatherSensorReading createNewReading(
        @RequestParam(name = "sensorId") Integer sensorId,
        @RequestParam(name = "readingValue") double readingValue,
        @RequestParam(name = "readingDateTime") Timestamp readingDateTime
    ) {
        WeatherSensorReading weatherSensorReading = new WeatherSensorReading(
            sensorId,
            readingValue,
            readingDateTime      
        );
        return weatherSensorService.createNewReading(weatherSensorReading);
    }
    
    @RequestMapping(path = "/weatherSensors", method = RequestMethod.GET )
    public List<WeatherSensor> getAllWeatherSensors() {
        return weatherSensorService.getAllWeatherSensors();
    }

    @RequestMapping(path = "/weatherSensorReadings", method = RequestMethod.GET )
    public List<WeatherSensorReading> getAllWeatherSensorReadings() {
        return weatherSensorService.getAllWeatherSensorReadings();
    }
    
}
