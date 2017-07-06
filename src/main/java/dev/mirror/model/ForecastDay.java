package dev.mirror.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import dev.mirror.precondition.Preconditions;

import java.math.BigDecimal;
import java.util.Objects;

@JsonDeserialize(builder = ForecastDay.Builder.class)
public class ForecastDay {

    public final long time;
    public final String day;
    public final String icon;
    public final BigDecimal low;
    public final BigDecimal high;

    public ForecastDay(Builder builder) {
        this.time = builder.time;
        this.day = Preconditions.checkNotEmpty(builder.day, "day");
        this.icon = Preconditions.checkNotEmpty(builder.icon, "icon");
        this.low = Preconditions.checkNotNull(builder.low, "low");
        this.high = Preconditions.checkNotNull(builder.high, "high");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ForecastDay that = (ForecastDay) other;
        return time == that.time &&
                Objects.equals(day, that.day) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(low, that.low) &&
                Objects.equals(high, that.high);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, day, icon, low, high);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("time", time)
                .add("day", day)
                .add("icon", icon)
                .add("low", low)
                .add("high", high)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private long time;
        private String day;
        private String icon;
        private BigDecimal low;
        private BigDecimal high;

        private Builder() {}

        public Builder withTime(long time) {
            this.time = time;
            return this;
        }

        public Builder withDay(String day) {
            this.day = day;
            return this;
        }

        public Builder withIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder withLow(BigDecimal low) {
            this.low = low;
            return this;
        }

        public Builder withHigh(BigDecimal high) {
            this.high = high;
            return this;
        }

        public ForecastDay build() {
            return new ForecastDay(this);
        }
    }
}
