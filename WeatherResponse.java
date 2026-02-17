package com.weather.app.model;

import java.util.List;

public class WeatherResponse {

    private String city;
    private List<ForecastData> forecast;

    public WeatherResponse(String city, List<ForecastData> forecast) {
        this.city = city;
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public List<ForecastData> getForecast() {
        return forecast;
    }
}
