package dev.mirror.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import dev.mirror.precondition.Preconditions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = ScheduleDay.Builder.class)
public class ScheduleDay {
    public final LocalDate date;
    public final ImmutableList<ScheduleEvent> events;

    private ScheduleDay(Builder builder) {
        this.date = Preconditions.checkNotNull(builder.date, "date");
        this.events = ImmutableList.copyOf(builder.events);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ScheduleDay that = (ScheduleDay) other;
        return Objects.equals(date, that.date) && Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, events);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("date", date)
                .add("events", events)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate date;
        private List<ScheduleEvent> events = new ArrayList<>();

        private Builder() {}

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder addEvent(ScheduleEvent event) {
            this.events.add(event);
            return this;
        }

        public Builder withEvents(List<ScheduleEvent> events) {
            this.events = new ArrayList<>(events);
            return this;
        }

        public ScheduleDay build() {
            return new ScheduleDay(this);
        }
    }
}
