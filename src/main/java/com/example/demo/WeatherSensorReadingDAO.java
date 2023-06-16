package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// jpa repo removes boiler plate code dao type code
public interface WeatherSensorReadingDAO extends JpaRepository<WeatherSensorReading, Integer> {

    List<WeatherSensorReading> findAllBySensorId(Integer sensorId);

    List<WeatherSensorReading> findAllBySensorIds(List<Integer> sensorId);
}