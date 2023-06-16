package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WeatherSensor")
public class WeatherSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="sensorType")
    private String sensorType;
    
    public WeatherSensor() {
    }

    public WeatherSensor(String sensorType) {
        this.sensorType = sensorType;
    }

    public WeatherSensor(Integer id, String sensorType) {
        this.id = id;
        this.sensorType = sensorType;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getSensorType() {
        return sensorType;
    }
}

