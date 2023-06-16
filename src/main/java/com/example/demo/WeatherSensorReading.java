package com.example.demo;

import java.sql.Timestamp;

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

    // @ManyToOne
    // @JoinColumn(name = "sensorId")
    // private WeatherSensor weatherSensor;

    @Column(name = "readingValue")
    private double readingValue;

    @Column(name = "readingDateTime")
    private Timestamp readingDateTime;

    public WeatherSensorReading() {
    }

    public WeatherSensorReading(int sensorId, double readingValue, Timestamp readingDateTime) {
        // this.weatherSensor = weatherSensor;
        this.sensorId = sensorId;
        this.readingValue = readingValue;
        this.readingDateTime = readingDateTime;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // public WeatherSensor getWeatherSensor() {
    //     return weatherSensor;
    // }

    // public void setWeatherSensor(WeatherSensor weatherSensor) {
    //     this.weatherSensor = weatherSensor;
    // }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(double readingValue) {
        this.readingValue = readingValue;
    }

    public Timestamp getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(Timestamp readingDateTime) {
        this.readingDateTime = readingDateTime;
    }
}
