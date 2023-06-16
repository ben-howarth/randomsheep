package com.example.demo;

import com.example.demo.domain.MetricType;
import com.example.demo.requests.CreateReadingRequest;
import com.example.demo.requests.GetStatisticsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateReading() throws Exception {
        CreateReadingRequest request = new CreateReadingRequest(
            1,
            31.0,
            "HUMIDITY"
        );
        
        String requestJson = objectMapper.writeValueAsString(
            request
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/weatherSensorReading")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testGetStatisticsWorks() throws Exception {
        Integer sensorId = new Random().nextInt();
        String setupRequestJson = objectMapper.writeValueAsString(
            new CreateReadingRequest(
            sensorId,
            31.0,
            "HUMIDITY"
        )
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/weatherSensorReading")
                .content(setupRequestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        GetStatisticsRequest getStatisticsRequest = new GetStatisticsRequest(
            List.of(sensorId),
            31,
            "SUM",
            List.of("HUMIDITY")
        );
        
        String requestJson = objectMapper.writeValueAsString(
            getStatisticsRequest
        );

        Map<MetricType, Double> responseMap = Map.of(MetricType.HUMIDITY, 31.0);

        String responseJson = objectMapper.writeValueAsString(responseMap);

        mockMvc.perform(MockMvcRequestBuilders.get("/getStatistics").content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
