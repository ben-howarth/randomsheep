package com.example.demo;

public enum MetricType {
    HUMIDITY("Humidity"),
    TEMPERATURE("Temperature"),
    WIND_SPEED("WindSpeed"),
    UV_INDEX("UvIndex");
    
    private final String type;
    
    MetricType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}