package dev.mirror.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import dev.mirror.precondition.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static dev.mirror.precondition.Preconditions.checkNotEmpty;
import static dev.mirror.precondition.Preconditions.checkNotNull;

/**
 * Weather represents the current temperature and upcoming forecast
 */
@JsonDeserialize(builder = Weather.Builder.class)
public class Weather {

    public final CurrentWeather current;
    public final ImmutableList<ForecastDay> forecast;

    private Weather(Builder builder) {
        this.current = checkNotNull(builder.current, "current");
        this.forecast = ImmutableList.copyOf(checkNotEmpty(builder.forecast, "forecast"));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Weather weather = (Weather) other;
        return Objects.equals(current, weather.current) && Objects.equals(forecast, weather.forecast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, forecast);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("current", current)
                .add("forecast", forecast)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private CurrentWeather current;
        private List<ForecastDay> forecast = new ArrayList<>();

        private Builder() {}

        public Builder withCurrent(CurrentWeather current) {
            this.current = current;
            return this;
        }

        public Builder addForecastDay(ForecastDay forecastDay) {
            forecast.add(Preconditions.checkNotNull(forecastDay, "forecastDay"));
            return this;
        }

        public Builder withForecast(Collection<ForecastDay> forecast) {
            this.forecast = new ArrayList<>(Preconditions.checkNotNull(forecast, "forecast"));
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}
