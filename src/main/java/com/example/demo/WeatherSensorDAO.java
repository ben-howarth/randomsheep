package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// jpa repo removes need to write boiler plate data access code  
public interface WeatherSensorDAO extends  JpaRepository<WeatherSensor,Integer> {

    List<WeatherSensor> findBySensorType(String sensorType);

    Optional<WeatherSensor> findById(Integer id);
}