package com.weather.app.model;

public class ForecastData {

    private String date;
    private double temperature;
    private double humidity;

    public ForecastData(String date, double temperature, double humidity) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}
