package dev.mirror.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mirror.precondition.PreconditionFailedException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final CurrentWeather CURRENT = CurrentWeather.newBuilder()
            .withIcon("clear-day")
            .withTemperature(new BigDecimal(80))
            .withSummary("Clear")
            .build();

    private static final ForecastDay FORECAST_DAY = ForecastDay.newBuilder()
            .withTime(1499151600)
            .withDay("Today")
            .withIcon("rain")
            .withLow(new BigDecimal(61))
            .withHigh(new BigDecimal(90))
            .build();

    @Test
    public void roundTripJson() throws Exception {
        Weather weather = Weather.newBuilder()
                .withCurrent(CURRENT)
                .addForecastDay(FORECAST_DAY)
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(weather);

        Weather deserialized = OBJECT_MAPPER.readValue(json, Weather.class);

        assertThat(deserialized).isEqualTo(weather);
    }

    @Test(expected = PreconditionFailedException.class)
    public void currentIsRequired() {
        Weather.newBuilder()
                .addForecastDay(FORECAST_DAY)
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void atLeastOneForecastDayRequired() {
        Weather.newBuilder()
                .withCurrent(CURRENT)
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void forecastDayCannotBeNull() {
        Weather.newBuilder()
                .withCurrent(CURRENT)
                .addForecastDay(null)
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void withForecastCannotBeNull() {
        Weather.newBuilder()
                .withCurrent(CURRENT)
                .withForecast(null)
                .build();
    }
}