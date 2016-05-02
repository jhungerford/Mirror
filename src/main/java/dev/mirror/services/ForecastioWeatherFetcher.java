package dev.mirror.services;

import dev.mirror.config.WeatherConfiguration;

public class ForecastioWeatherFetcher implements WeatherFetcher {

    private final String apiKey;

    public ForecastioWeatherFetcher(String apiKey, double latitude, double longitude) {
        this.apiKey = apiKey;
    }

    @Override
    public String fetchWeatherJson() {
        return null;
    }

}
