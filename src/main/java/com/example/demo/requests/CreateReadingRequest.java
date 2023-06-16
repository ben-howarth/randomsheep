package com.example.demo.requests;

public class CreateReadingRequest {
    public Integer sensorId;
    public double readingValue;
    public String metric;
    
    public CreateReadingRequest() {
    }

    public CreateReadingRequest(Integer sensorId, double readingValue, String metric) {
        this.sensorId = sensorId;
        this.readingValue = readingValue;
        this.metric = metric;
    }

    
}
