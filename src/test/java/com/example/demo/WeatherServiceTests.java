package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.domain.MetricType;
import com.example.demo.domain.StatisticType;
import com.example.demo.domain.WeatherSensorReading;
import com.example.demo.repositories.WeatherSensorReadingDAO;
import com.example.demo.services.WeatherSensorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTests {

    @Mock
    private WeatherSensorReadingDAO weatherSensorReadingDAO;

    @InjectMocks
    private WeatherSensorService weatherSensorService;

    @Test
    public void testCalculateAverage() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));

        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> averageResult = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            31,
            StatisticType.AVERAGE, 
            List.of(MetricType.HUMIDITY)
        );
        
        Map<MetricType, Double> expectedResult = Map.of(MetricType.HUMIDITY, 30.0);
        
        assertEquals(expectedResult, averageResult);
    }

    @Test
    public void testCalculateMin() {
        Integer sensorId = 1;
         List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));

        
        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> expectedResult = Map.of(MetricType.UV_INDEX, 0.0);

        Map<MetricType, Double> minResult = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            31,
            StatisticType.MIN, 
            List.of(MetricType.UV_INDEX)
        );


        assertEquals(expectedResult, minResult);
    }

    @Test
    public void testCalculateMax() {
        Integer sensorId = 1;
         List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));


        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);
        
        Map<MetricType, Double> expectedResult = Map.of(MetricType.TEMPERATURE, 45.0);

        Map<MetricType, Double> maxResult = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            31,
            StatisticType.MAX, 
            List.of(MetricType.TEMPERATURE)
        );

        assertEquals(expectedResult, maxResult);
    }

    @Test
    public void testCalculateSum() {
        Integer sensorId = 1;
         List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));


        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> sumResults = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            31,
            StatisticType.SUM, 
            List.of(MetricType.HUMIDITY)
        );

        Map<MetricType, Double> expectedResult = Map.of(MetricType.HUMIDITY, 120.0);

        assertEquals(expectedResult, sumResults);
    }

    @Test
    public void testMutlipleMetrics() {
        Integer sensorId = 1;
         List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));


        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> sumResults = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            31,
            StatisticType.SUM, 
            List.of(MetricType.HUMIDITY, MetricType.TEMPERATURE, MetricType.UV_INDEX, MetricType.WIND_SPEED)
        );

        Map<MetricType, Double> expectedResult = Map.of(MetricType.TEMPERATURE, 120.00, MetricType.UV_INDEX, 175.00, MetricType.WIND_SPEED, 240.00, MetricType.HUMIDITY, 120.0);

        assertEquals(expectedResult, sumResults);
    }

    @Test
    public void testDefaultDate() {
        Integer sensorId = 1;
         List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));


        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> sumResults = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            null,
            StatisticType.AVERAGE, 
            List.of(MetricType.TEMPERATURE, MetricType.UV_INDEX, MetricType.WIND_SPEED, MetricType.HUMIDITY)
        );

        Map<MetricType, Double> expectedResult = Map.of(MetricType.TEMPERATURE, 30.00, MetricType.UV_INDEX, 25.00, MetricType.WIND_SPEED, 30.00, MetricType.HUMIDITY, 30.0);

        assertEquals(expectedResult, sumResults);
    }

     @Test
    public void testOnlyIncludeDatesInRange() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = TestData.getTestWeatherDataList(List.of(sensorId));

        when(weatherSensorReadingDAO.findAllBySensorIdIn(List.of(1))).thenReturn(weatherDataList);

        Map<MetricType, Double> averageResults = weatherSensorService.getStatisticsByMetric(
            List.of(sensorId),
            2,
            StatisticType.SUM, 
            List.of(MetricType.HUMIDITY)
        );

        Map<MetricType, Double> expectedResult = Map.of(MetricType.HUMIDITY, 120.00);


        assertEquals(expectedResult, averageResults);
    }

    @Test
    public void testExceptionIsThrown() {
        when(weatherSensorReadingDAO.findAllBySensorId(99)).thenReturn(
            new ArrayList<WeatherSensorReading>()
        );

        
        Exception exception = assertThrows(Exception.class, () -> {
            weatherSensorService.getStatisticsByMetric(
            List.of(99),
            31,
            StatisticType.SUM, 
            List.of(MetricType.HUMIDITY)
        );
        });
        assertEquals(exception.getMessage(), "No readings exist for this sensor in this time range.");
    }

}

