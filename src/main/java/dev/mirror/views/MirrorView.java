package dev.mirror.views;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.dropwizard.views.View;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

public class MirrorView extends View {

    private final DateTime time;
    private final Weather weather;
    private final Radar radar;

    private MirrorView(DateTime time, Weather weather, Radar radar) {
        super("mirror.ftl", Charsets.UTF_8);
        this.time = time;
        this.weather = weather;
        this.radar = radar;
    }

    public DateTime getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }

    public Radar getRadar() {
        return radar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MirrorView that = (MirrorView) o;

        return new EqualsBuilder()
                .append(time, that.time)
                .append(weather, that.weather)
                .append(radar, that.radar)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(time)
                .append(weather)
                .append(radar)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DateTime time;
        private Weather weather;
        private Radar radar;

        private Builder() {}

        public Builder withTime(DateTime time) {
            this.time = time;
            return this;
        }

        public Builder withWeather(Weather weather) {
            this.weather = weather;
            return this;
        }

        public Builder withRadar(Radar radar) {
            this.radar = radar;
            return this;
        }

        public MirrorView build() {
            Preconditions.checkNotNull(time, "Time is missing");
            Preconditions.checkNotNull(weather, "Weather is missing");
            Preconditions.checkNotNull(radar, "Radar is missing");

            return new MirrorView(time, weather, radar);
        }
    }
}
