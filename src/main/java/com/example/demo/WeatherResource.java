package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public Map<MetricType, Double> getStatisticForWeatherSensor(
        @RequestParam("ids") List<Integer> ids,
        @RequestParam(name = "dateRange") Integer dateRange,
        @RequestParam(name = "statistic") String statistic,
        @RequestParam(name = "statistic") List<String> metrics
        ) {
        StatisticType statisticType = StatisticType.valueOf(statistic);
        List<MetricType> metricTypes = metrics.stream().map(metric -> MetricType.valueOf(metric)).toList();
        return weatherSensorService.getStatisticsByMetric(ids, dateRange, statisticType, metricTypes);
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
        @RequestParam(name = "readingDateTime") LocalDateTime readingDateTime,
        @RequestParam(name = "metric") String metric
    ) {
        WeatherSensorReading weatherSensorReading = new WeatherSensorReading(
            sensorId,
            readingValue,
            readingDateTime, 
            metric
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
