package dev.mirror.fetcher;

import dev.mirror.model.Weather;

public interface WeatherFetcher {
    /**
     * Fetch the current weather.  Implementations should always fetch the latest weather -
     * callers of {@code currentWeather} may cache the result.
     *
     * @return Current weather
     */
    Weather currentWeather();
}
