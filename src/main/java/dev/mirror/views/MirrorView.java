package dev.mirror.views;

import com.google.common.base.Charsets;
import io.dropwizard.views.View;
import org.joda.time.DateTime;

public class MirrorView extends View {

    private final DateTime time;

    private MirrorView(DateTime time) {
        super("mirror.ftl", Charsets.UTF_8);
        this.time = time;
    }

    public DateTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MirrorView that = (MirrorView) o;

        return time != null ? time.equals(that.time) : that.time == null;

    }

    @Override
    public int hashCode() {
        return time != null ? time.hashCode() : 0;
    }

    public static MirrorViewBuilder builder() {
        return new MirrorViewBuilder();
    }

    public static class MirrorViewBuilder {
        private DateTime time;

        private MirrorViewBuilder() {}

        public MirrorViewBuilder setTime(DateTime time) {
            this.time = time;
            return this;
        }

        public MirrorView build() {
            return new MirrorView(time);
        }
    }
}
