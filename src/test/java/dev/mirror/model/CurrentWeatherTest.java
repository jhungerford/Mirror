package dev.mirror.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mirror.precondition.PreconditionFailedException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void roundTripJson() throws Exception {
        CurrentWeather current = CurrentWeather.newBuilder()
                .withIcon("clear-day")
                .withTemperature(new BigDecimal(80))
                .withSummary("Clear")
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(current);

        CurrentWeather deserialized = OBJECT_MAPPER.readValue(json, CurrentWeather.class);

        assertThat(deserialized).isEqualTo(current);
    }

    @Test(expected = PreconditionFailedException.class)
    public void iconIsRequired() {
        CurrentWeather.newBuilder()
                .withTemperature(new BigDecimal(80))
                .withSummary("Clear")
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void temperatureIsRequired() {
        CurrentWeather.newBuilder()
                .withIcon("clear-day")
                .withSummary("Clear")
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void summaryIsRequired() {
        CurrentWeather.newBuilder()
                .withIcon("clear-day")
                .withTemperature(new BigDecimal(80))
                .build();
    }
}