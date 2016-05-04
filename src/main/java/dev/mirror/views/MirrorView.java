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

    private MirrorView(DateTime time, Weather weather) {
        super("mirror.ftl", Charsets.UTF_8);
        this.time = time;
        this.weather = weather;
    }

    public DateTime getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MirrorView that = (MirrorView) o;

        return new EqualsBuilder()
                .append(time, that.time)
                .append(weather, that.weather)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(time)
                .append(weather)
                .toHashCode();
    }

    public static MirrorViewBuilder builder() {
        return new MirrorViewBuilder();
    }

    public static class MirrorViewBuilder {
        private DateTime time;
        private Weather weather;

        private MirrorViewBuilder() {}

        public MirrorViewBuilder setTime(DateTime time) {
            this.time = time;
            return this;
        }

        public MirrorViewBuilder setWeather(Weather weather) {
            this.weather = weather;
            return this;
        }

        public MirrorView build() {
            Preconditions.checkNotNull(time, "Time is missing");
            Preconditions.checkNotNull(weather, "Weather is missing");

            return new MirrorView(time, weather);
        }
    }
}
