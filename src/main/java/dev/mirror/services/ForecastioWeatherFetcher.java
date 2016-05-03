package dev.mirror.services;

import java.io.IOException;

public class ForecastioWeatherFetcher implements WeatherFetcher {

    private final String apiKey;

    public ForecastioWeatherFetcher(String apiKey, double latitude, double longitude) {
        this.apiKey = apiKey;
    }

    @Override
    public String fetchWeatherJson() throws IOException {
        return null;
    }

}
