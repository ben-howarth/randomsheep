package com.example.demo;

public class WeatherSensor {
    private String sensorId;
    private String sensorType;
    private double sensorValue;
    
    public WeatherSensor(String sensorId, String sensorType) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorValue = 0.0; // Default sensor value
    }
    
    public String getSensorId() {
        return sensorId;
    }
    
    public String getSensorType() {
        return sensorType;
    }
    
    public double getSensorValue() {
        return sensorValue;
    }
    
    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }
    
    public void updateSensorValue(double newValue) {
        this.sensorValue = newValue;
    }
}

