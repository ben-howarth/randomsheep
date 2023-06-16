package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTests {


    @Mock
    private WeatherSensorDAO weatherSensorDAO;
    @Mock
    private WeatherSensorReadingDAO weatherSensorReadingDAO;

    @InjectMocks
    private WeatherSensorService weatherSensorService;

    @Test
    public void testCalculateAverage() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = Arrays.asList(
            new WeatherSensorReading(
                sensorId, 20.0, Timestamp.valueOf("2023-06-15 10:00:00")
            )
            , new WeatherSensorReading(
                sensorId, 25.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ), new WeatherSensorReading(
                sensorId, 30.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ));

        when(weatherSensorReadingDAO.findAllBySensorId(1)).thenReturn(weatherDataList);

        double averageValue = weatherSensorService.getStatisticsForWeatherSensor(
            sensorId,
            Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.AVERAGE
        );

        assertEquals(25.0, averageValue, 0.01);
    }

    @Test
    public void testCalculateMin() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = Arrays.asList(
            new WeatherSensorReading(
                sensorId, 20.0, Timestamp.valueOf("2023-06-15 10:00:00")
            )
            , new WeatherSensorReading(
                sensorId, 25.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ), new WeatherSensorReading(
                sensorId, 30.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ));

        when(weatherSensorReadingDAO.findAllBySensorId(1)).thenReturn(weatherDataList);

        double minValue = weatherSensorService.getStatisticsForWeatherSensor(
            sensorId,
             Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.MIN
        );

        assertEquals(20.0, minValue, 0.01);
    }

    @Test
    public void testCalculateMax() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = Arrays.asList(
            new WeatherSensorReading(
                sensorId, 20.0, Timestamp.valueOf("2023-06-10 10:00:00")
            )
            , new WeatherSensorReading(
                sensorId, 25.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ), new WeatherSensorReading(
                sensorId, 30.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ),
            new WeatherSensorReading(
                sensorId, 45.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ));

        when(weatherSensorReadingDAO.findAllBySensorId(1)).thenReturn(weatherDataList);

        double maxValue = weatherSensorService.getStatisticsForWeatherSensor(
            sensorId,
            Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.MAX
        );

        assertEquals(45.0, maxValue, 0.01);
    }

    @Test
    public void testCalculateSum() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = Arrays.asList(
            new WeatherSensorReading(
                sensorId, 20.0, Timestamp.valueOf("2023-06-10 10:00:00")
            )
            , new WeatherSensorReading(
                sensorId, 25.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ), new WeatherSensorReading(
                sensorId, 30.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ),
            new WeatherSensorReading(
                sensorId, 45.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ));

        when(weatherSensorReadingDAO.findAllBySensorId(1)).thenReturn(weatherDataList);

        double sum = weatherSensorService.getStatisticsForWeatherSensor(
            sensorId,
             Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.SUM
        );

        assertEquals(100.0, sum, 0.01);
    }

     @Test
    public void testOnlyIncludeDatesInRange() {
        Integer sensorId = 1;
        List<WeatherSensorReading> weatherDataList = Arrays.asList(
            new WeatherSensorReading(
                sensorId, 20.0, Timestamp.valueOf("2023-06-10 10:00:00")
            )
            , new WeatherSensorReading(
                sensorId, 25.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ), new WeatherSensorReading(
                sensorId, 30.0, Timestamp.valueOf("2023-06-15 10:00:00")
            ),
            new WeatherSensorReading(
                sensorId, 45.0, Timestamp.valueOf("2024-06-15 10:00:00")
            ));

        when(weatherSensorReadingDAO.findAllBySensorId(1)).thenReturn(weatherDataList);

        double averageValue = weatherSensorService.getStatisticsForWeatherSensor(
            sensorId,
             Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.AVERAGE
        );

        assertEquals(27.5, averageValue, 0.01);
    }

    @Test
    public void testExceptionIsThrown() {
        when(weatherSensorReadingDAO.findAllBySensorId(99)).thenReturn(
            new ArrayList<WeatherSensorReading>()
        );

        
        Exception exception = assertThrows(Exception.class, () -> {
            weatherSensorService.getStatisticsForWeatherSensor(
            99,
             Timestamp.valueOf("2023-06-14 10:00:00"),
            Timestamp.valueOf("2023-06-16 10:00:00"),
            StatisticType.SUM
        );
        });
        assertEquals(exception.getMessage(), "No readings exist for this sensor in this time range.");
    }

}

