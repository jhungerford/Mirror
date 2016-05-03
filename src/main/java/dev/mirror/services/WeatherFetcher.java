package dev.mirror.services;

import java.io.IOException;

public interface WeatherFetcher {
    String fetchWeatherJson() throws IOException;
}
