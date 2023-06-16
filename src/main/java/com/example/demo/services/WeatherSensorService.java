package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MetricType;
import com.example.demo.domain.StatisticType;
import com.example.demo.domain.WeatherSensorReading;
import com.example.demo.repositories.WeatherSensorReadingDAO;

@Service
public class WeatherSensorService {

    @Autowired
    WeatherSensorReadingDAO weatherSensorReadingDAO;

    public WeatherSensorReading createNewReading(
         WeatherSensorReading weatherSensorReading
    ) {
        return weatherSensorReadingDAO.save(weatherSensorReading);
    }

    public List<WeatherSensorReading> getAllWeatherSensorReadings() {
        List<WeatherSensorReading> weatherSensorReadings = StreamSupport.stream(weatherSensorReadingDAO.findAll().spliterator(), false).toList();
        return weatherSensorReadings;
    }

    public HashMap<MetricType, Double> getStatisticsByMetric(
        List<Integer> ids, Integer dateRange, StatisticType statisticType, List<MetricType> metricTypes
        ) {
            HashMap<MetricType, Double> metricResults = new HashMap<MetricType, Double>();
            for (MetricType metric: metricTypes) {
                metricResults.put(metric, getStatisticsForWeatherSensor(ids, dateRange, statisticType, metric));
            }
            return metricResults;
        
    }

    public Double getStatisticsForWeatherSensor(
        List<Integer> ids, Integer dateRange, StatisticType statisticType, MetricType metricType
        ) {
            switch(statisticType) {
                case AVERAGE:
                    return getAverageReadings(ids, dateRange, metricType);
                case MAX:
                    return getMaximumReadings(ids, dateRange, metricType);
                case MIN:
                    return getMinimumReadings(ids, dateRange, metricType);
                case SUM: 
                    return getSumOfReadings(ids, dateRange, metricType);
                default:
                    return 0.0;
            }
        
    }

    public Double getAverageReadings(List<Integer> id, Integer dateRange, MetricType metricType ) {
        List<WeatherSensorReading> weatherSensorReadingsForMetric = 
            getReadingsInDateRange(id, dateRange).stream()
                .filter(reading ->
                    MetricType.valueOf(reading.getReadingType()).equals(metricType)
                ).toList();
        
        Double sum = weatherSensorReadingsForMetric.stream()
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .sum();

        Integer numberOfReadings = weatherSensorReadingsForMetric.size();

        return sum/numberOfReadings ;
    }

    public Double getMaximumReadings(List<Integer> id, Integer dateRange, MetricType metricType) {
        return getReadingsInDateRange(id, dateRange).stream()
            .filter(reading -> MetricType.valueOf(reading.getReadingType()).equals(metricType))
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .max().getAsDouble();
    }

    public Double getMinimumReadings(List<Integer> id, Integer dateRange, MetricType metricType) {        
        return getReadingsInDateRange(id, dateRange).stream()
            .filter(reading -> MetricType.valueOf(reading.getReadingType()).equals(metricType))
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .min().getAsDouble();
    }

    public Double getSumOfReadings(List<Integer> id, Integer dateRange, MetricType metricType) {
        return getReadingsInDateRange(id, dateRange).stream()
            .filter(reading -> MetricType.valueOf(reading.getReadingType()).equals(metricType))
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .sum();

    }

    public List<WeatherSensorReading> getReadingsInDateRange (List<Integer> ids, Integer dateRange) {
        List<WeatherSensorReading> WeatherSensorReadings = weatherSensorReadingDAO.findAllBySensorIdIn(ids);
        Integer dateRangeOrDefault = dateRange== null ? 1 : dateRange; // use only the most recent days readings if date range is null
        List<WeatherSensorReading> weatherSensorReadingsInRange =  WeatherSensorReadings.stream()
            .filter(reading -> 
                reading.getReadingDateTime().compareTo(reading.getReadingDateTime().minusDays(dateRangeOrDefault)) >= 0 
            ).toList();
            
       if (weatherSensorReadingsInRange == null || weatherSensorReadingsInRange.isEmpty()) {
            throw new IllegalArgumentException("No readings exist for this sensor in this time range.");
        }

        List<WeatherSensorReading> weatherSensorReadingsForMetric =  WeatherSensorReadings.stream()
            .filter(reading -> 
                reading.getReadingDateTime().compareTo(reading.getReadingDateTime().minusDays(dateRangeOrDefault)) >= 0 
            ).toList();
            
       if (weatherSensorReadingsForMetric == null || weatherSensorReadingsForMetric.isEmpty()) {
            throw new IllegalArgumentException("No readings exist for the metrics provided for sensor in this time range.");
        }

        return weatherSensorReadingsInRange;
    }

}