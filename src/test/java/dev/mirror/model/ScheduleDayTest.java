package dev.mirror.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleDayTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    @Test
    public void jsonNoEvents() throws Exception {
        ScheduleDay day = ScheduleDay.newBuilder()
                .withDate(LocalDate.of(2017, 7, 3))
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(day);

        ScheduleDay deserialized = OBJECT_MAPPER.readValue(json, ScheduleDay.class);

        assertThat(deserialized).isEqualTo(day);
    }

    @Test
    public void jsonWithEvents() throws Exception {
        ScheduleDay day = ScheduleDay.newBuilder()
                .withDate(LocalDate.of(2017, 7, 4))
                .addEvent(ScheduleEvent.newBuilder()
                        .withId("1")
                        .withDescription("Independence Day")
                        .build())
                .addEvent(ScheduleEvent.newBuilder()
                        .withId("2")
                        .withStart(Instant.parse("2017-07-04T14:30:00.000Z"))
                        .withDescription("Coffee")
                        .build())
                .addEvent(ScheduleEvent.newBuilder()
                        .withId("3")
                        .withStart(Instant.parse("2017-07-04T16:30:00.000Z"))
                        .withDescription("Engineering Leads Daily")
                        .build())
                .addEvent(ScheduleEvent.newBuilder()
                        .withId("4")
                        .withStart(Instant.parse("2017-07-04T17:00:00.000Z"))
                        .withDescription("Standup")
                        .build())
                .addEvent(ScheduleEvent.newBuilder()
                        .withId("5")
                        .withStart(Instant.parse("2017-07-04T19:00:00.000Z"))
                        .withDescription("Event with a name that\'s too long to fit in a row comfortably.")
                        .build())
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(day);

        ScheduleDay deserialized = OBJECT_MAPPER.readValue(json, ScheduleDay.class);

        assertThat(deserialized).isEqualTo(day);
    }
}