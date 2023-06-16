package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.WeatherSensorReading;

// jpa repo removes boiler plate code dao type code
public interface WeatherSensorReadingDAO extends JpaRepository<WeatherSensorReading, Integer> {

    List<WeatherSensorReading> findAllBySensorId(Integer sensorId);

    List<WeatherSensorReading> findAllBySensorIdIn(List<Integer> sensorId);
}