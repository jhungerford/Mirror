package dev.mirror;

import dev.mirror.model.CurrentWeather;
import dev.mirror.model.ForecastDay;
import dev.mirror.model.Weather;
import dev.mirror.fetcher.WeatherFetcher;

import java.math.BigDecimal;

/**
 * Weather fetcher that returns mockup weather data so API tests don't have to hit the dark sky api.
 */
public class MockupWeatherFetcher implements WeatherFetcher {

    private static final Weather MOCKUP_WEATHER = Weather.newBuilder()
            .withCurrent(CurrentWeather.newBuilder()
                    .withIcon("clear-day")
                    .withTemperature(new BigDecimal(81))
                    .withSummary("Clear")
                    .build())
            .addForecastDay(ForecastDay.newBuilder()
                    .withTime(1499151600L)
                    .withDay("Today")
                    .withIcon("rain")
                    .withHigh(new BigDecimal(90))
                    .withLow(new BigDecimal(61))
                    .build())
            .addForecastDay(ForecastDay.newBuilder()
                    .withTime(1499238000L)
                    .withDay("Tue")
                    .withIcon("partly-cloudy-day")
                    .withHigh(new BigDecimal(89))
                    .withLow(new BigDecimal(66))
                    .build())
            .addForecastDay(ForecastDay.newBuilder()
                    .withTime(1499324400L)
                    .withDay("Wed")
                    .withIcon("partly-cloudy-day")
                    .withHigh(new BigDecimal(96))
                    .withLow(new BigDecimal(65))
                    .build())
            .build();

    @Override
    public Weather currentWeather() {
        return MOCKUP_WEATHER;
    }
}
