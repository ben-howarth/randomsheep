package com.example.demo.requests;

import java.util.List;

public class GetStatisticsRequest {
    public List<Integer> ids;
    public Integer dateRange;
    public String statistic;
    public List<String> metrics;

    public GetStatisticsRequest(List<Integer> ids, Integer dateRange, String statistic, List<String> metrics) {
        this.ids = ids;
        this.dateRange = dateRange;
        this.statistic = statistic;
        this.metrics = metrics;
    }

    public GetStatisticsRequest() {
    }
}