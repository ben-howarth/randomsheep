package com.example.demo;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class WeatherSensorService {

    @Autowired
    WeatherSensorDAO weatherSensorDAO;

    @Autowired
    WeatherSensorReadingDAO weatherSensorReadingDAO;

    public WeatherSensor getWeatherSensorById(
        @PathVariable("id") Integer id) {
        return weatherSensorDAO.findById(id).get();
    }

    public WeatherSensor createNewSensor(WeatherSensor weatherSensor) {
        return weatherSensorDAO.save(weatherSensor);
    }

    public WeatherSensorReading createNewReading(
         WeatherSensorReading weatherSensorReading
    ) {
        return weatherSensorReadingDAO.save(weatherSensorReading);
    }
    
    public List<WeatherSensor> getAllWeatherSensors() {
        List<WeatherSensor> weatherSensors = StreamSupport.stream(weatherSensorDAO.findAll().spliterator(), false).toList();
        return weatherSensors;
    }

    public List<WeatherSensorReading> getAllWeatherSensorReadings() {
        List<WeatherSensorReading> weatherSensorReadings = StreamSupport.stream(weatherSensorReadingDAO.findAll().spliterator(), false).toList();
        return weatherSensorReadings;
    }

    public Double getStatisticsForWeatherSensor(
        List<Integer> ids, Timestamp startDate, Timestamp endDate, StatisticType statisticType
        ) {
         switch(statisticType) {
            case AVERAGE:
                 return getAverageReadingsForWeatherSensor(ids, startDate, endDate);
            case MAX:
                return getMaximumReadingsForWeatherSensor(ids, startDate, endDate);
            case MIN:
                return getMinimumReadingsForWeatherSensor(ids, startDate, endDate);
            case SUM: 
                return getSumOfReadingsForWeatherSensor(ids, startDate, endDate);
            default:
                return 0.0;
        }
        
    }

    public Double getAverageReadingsForWeatherSensor(List<Integer> id, Timestamp startDate, Timestamp endDate ) {
        List<WeatherSensorReading> weatherSensorReadingsInRange = 
            startDate!=null && endDate!=null ? 
                getReadingsInDateRange(id, startDate, endDate)
                : getReadingsInDateRange(id, startDate, endDate);
        
        Double sum = weatherSensorReadingsInRange.stream()
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .sum();
        Integer numberOfReadings = weatherSensorReadingsInRange.size();

       return sum/numberOfReadings ;
    }

    public Double getMaximumReadingsForWeatherSensor(List<Integer> id, Timestamp startDate, Timestamp endDate ) {
        return getReadingsInDateRange(id, startDate, endDate).stream()
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .max().getAsDouble();
    }

    public Double getMinimumReadingsForWeatherSensor(List<Integer> id, Timestamp startDate, Timestamp endDate ) {        
        return getReadingsInDateRange(id, startDate, endDate).stream()
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .min().getAsDouble();
    }

    public Double getSumOfReadingsForWeatherSensor(List<Integer> id, Timestamp startDate, Timestamp endDate ) {
        return getReadingsInDateRange(id, startDate, endDate).stream()
            .mapToDouble(WeatherSensorReading::getReadingValue)
            .sum();

    }

    public List<WeatherSensorReading> getReadingsInDateRange (List<Integer> ids, Timestamp startDate, Timestamp endDate) {
         List<WeatherSensorReading> weatherSensorReadingsInRange =  weatherSensorReadingDAO.findAllBySensorIds(ids).stream()
            .filter(reading -> 
                reading.getReadingDateTime().compareTo(startDate) >= 0 
                && reading.getReadingDateTime().compareTo(endDate) <= 0 
            ).toList();
            Timestamp.
       if (weatherSensorReadingsInRange == null || weatherSensorReadingsInRange.isEmpty()) {
            throw new IllegalArgumentException("No readings exist for this sensor in this time range.");
        }
        return weatherSensorReadingsInRange;
    }

}