package dev.mirror.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mirror.precondition.PreconditionFailedException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastDayTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void roundTripJson() throws Exception {
        ForecastDay day = ForecastDay.newBuilder()
                .withTime(1499151600)
                .withDay("Today")
                .withIcon("rain")
                .withLow(new BigDecimal(61))
                .withHigh(new BigDecimal(90))
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(day);

        ForecastDay deserialized = OBJECT_MAPPER.readValue(json, ForecastDay.class);

        assertThat(deserialized).isEqualTo(day);
    }

    @Test(expected = PreconditionFailedException.class)
    public void dayIsRequired() {
        ForecastDay.newBuilder()
                .withTime(1499151600)
                .withIcon("rain")
                .withLow(new BigDecimal(61))
                .withHigh(new BigDecimal(90))
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void iconIsRequired() {
        ForecastDay.newBuilder()
                .withTime(1499151600)
                .withDay("Today")
                .withLow(new BigDecimal(61))
                .withHigh(new BigDecimal(90))
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void lowIsRequired() {
        ForecastDay.newBuilder()
                .withTime(1499151600)
                .withDay("Today")
                .withIcon("rain")
                .withHigh(new BigDecimal(90))
                .build();
    }

    @Test(expected = PreconditionFailedException.class)
    public void highIsRequired() {
        ForecastDay.newBuilder()
                .withTime(1499151600)
                .withDay("Today")
                .withIcon("rain")
                .withLow(new BigDecimal(61))
                .build();
    }
}