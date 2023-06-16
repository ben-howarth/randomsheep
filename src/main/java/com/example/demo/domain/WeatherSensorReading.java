package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WeatherSensorReading")
public class WeatherSensorReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sensorId")
    private int sensorId;

    @Column(name = "readingValue")
    private double readingValue;

    @Column(name = "readingDateTime")
    private LocalDateTime readingDate;

    @Column(name="readingType")
    private String readingType;

    public WeatherSensorReading(int sensorId, double readingValue, LocalDateTime readingDate,
            String readingType) {
        this.sensorId = sensorId;
        this.readingValue = readingValue;
        this.readingDate = readingDate;
        this.readingType = readingType;
    }

    public WeatherSensorReading() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public double getReadingValue() {
        return readingValue;
    }

    public String getReadingType() {
        return readingType;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public void setReadingValue(double readingValue) {
        this.readingValue = readingValue;
    }

    public LocalDateTime getReadingDateTime() {
        return readingDate;
    }

    public void setReadingDateTime(LocalDateTime readingDate) {
        this.readingDate = readingDate;
    }
}
