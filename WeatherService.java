package com.weather.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.model.ForecastData;
import com.weather.app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherResponse getWeatherForecast(String city) {

        String url = apiUrl + "/forecast?q=" + city +
                "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(url, String.class);

        List<ForecastData> forecastList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            JsonNode list = root.path("list");

            // Take 5 entries (5 days approx.)
            for (int i = 0; i < list.size() && i < 5; i++) {

                JsonNode node = list.get(i);

                String date = node.path("dt_txt").asText();
                double temp = node.path("main").path("temp").asDouble();
                double humidity = node.path("main").path("humidity").asDouble();

                forecastList.add(new ForecastData(date, temp, humidity));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing weather data");
        }

        return new WeatherResponse(city, forecastList);
    }
}
