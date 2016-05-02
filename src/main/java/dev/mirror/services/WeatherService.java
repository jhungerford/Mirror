package dev.mirror.services;

import dev.mirror.views.Weather;

// TODO: cache weather responses - forecast.io has a limit of 1000 requests / day
public class WeatherService {

    private final WeatherFetcher weatherFetcher;

    public WeatherService(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public Weather getWeather() {
        String json = weatherFetcher.fetchWeatherJson();
        return parseJson(json);
    }

    public Weather parseJson(String json) {
        return null;
    }
}
