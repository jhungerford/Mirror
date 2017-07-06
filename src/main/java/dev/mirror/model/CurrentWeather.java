package dev.mirror.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import dev.mirror.precondition.Preconditions;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CurrentWeather contains the current weather conditions.
 */
@JsonDeserialize(builder = CurrentWeather.Builder.class)
public class CurrentWeather {

    public final String icon;
    public final BigDecimal temperature;
    public final String summary;

    private CurrentWeather(Builder builder) {
        this.icon = Preconditions.checkNotEmpty(builder.icon, "icon");
        this.temperature = Preconditions.checkNotNull(builder.temperature, "temperature");
        this.summary = Preconditions.checkNotEmpty(builder.summary, "summary");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        CurrentWeather that = (CurrentWeather) other;
        return Objects.equals(icon, that.icon)
                && Objects.equals(temperature, that.temperature)
                && Objects.equals(summary, that.summary);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("icon", icon)
                .add("temperature", temperature)
                .add("summary", summary)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, temperature, summary);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String icon;
        private BigDecimal temperature;
        private String summary;

        private Builder() {}

        public Builder withIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder withTemperature(BigDecimal temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public CurrentWeather build() {
            return new CurrentWeather(this);
        }
    }
}
