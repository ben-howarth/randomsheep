package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.domain.MetricType;
import com.example.demo.domain.StatisticType;
import com.example.demo.domain.WeatherSensorReading;
import com.example.demo.requests.CreateReadingRequest;
import com.example.demo.requests.GetStatisticsRequest;
import com.example.demo.services.WeatherSensorService;

@RestController
@EnableWebMvc
public class WeatherController {

    @Autowired
    WeatherSensorService weatherSensorService;

    @RequestMapping(path = "/getStatistics", method = RequestMethod.GET )
    public Map<MetricType, Double> getStatisticForWeatherSensor(
        @RequestBody GetStatisticsRequest getStatisticsRequest
        ) {
        StatisticType statisticType = StatisticType.valueOf(getStatisticsRequest.statistic);
        List<MetricType> metricTypes = getStatisticsRequest.metrics.stream().map(metric -> MetricType.valueOf(metric)).toList();
        return weatherSensorService.getStatisticsByMetric(getStatisticsRequest.ids, getStatisticsRequest.dateRange, statisticType, metricTypes);
    }

    @RequestMapping(path = "/weatherSensorReading", method = RequestMethod.POST )
    public WeatherSensorReading createNewReading(
        @RequestBody CreateReadingRequest createReadingRequest
    ) {
        WeatherSensorReading weatherSensorReading = new WeatherSensorReading(
            createReadingRequest.sensorId,
            createReadingRequest.readingValue,
            LocalDateTime.now(), 
            createReadingRequest.metric
        );
        return weatherSensorService.createNewReading(weatherSensorReading);
    }
    
}
