package dev.mirror.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import dev.mirror.precondition.Preconditions;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(builder = ScheduleEvent.Builder.class)
public class ScheduleEvent {

    public final String id;
    public final Optional<Instant> start;
    public final String description;

    private ScheduleEvent(Builder builder) {
        this.id = Preconditions.checkNotEmpty(builder.id, "id");
        this.start = Preconditions.checkNotNull(builder.start, "start");
        this.description = Preconditions.checkNotEmpty(builder.description, "description");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ScheduleEvent that = (ScheduleEvent) other;
        return Objects.equals(id, that.id)
                && Objects.equals(start, that.start)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, description);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("start", start)
                .add("description", description)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private Optional<Instant> start = Optional.empty();
        private String description;

        private Builder() {}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withStart(Instant start) {
            this.start = Optional.ofNullable(start);
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ScheduleEvent build() {
            return new ScheduleEvent(this);
        }
    }
}
