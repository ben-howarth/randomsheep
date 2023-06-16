package com.example.demo;


public enum StatisticType {
    AVERAGE("Average"),
    MIN("Min"),
    MAX("Max"),
    SUM("Sum");
    
    private final String type;
    
    StatisticType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}

