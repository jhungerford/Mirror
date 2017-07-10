package dev.mirror.api;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import dev.mirror.fetcher.WeatherFetcher;
import dev.mirror.model.ScheduleDay;
import dev.mirror.model.ScheduleEvent;
import dev.mirror.model.Weather;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Path("/v1/mirror")
@Produces(MediaType.APPLICATION_JSON)
public class MirrorResource {

    private final WeatherFetcher weatherFetcher;

    // Dark Sky API is limited to 1000 calls / day for free.  A call every 15 minutes = 96 calls / day.
    private final Cache<String, Weather> weatherCache = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build();

    /**
     * Constructs a new MirrorResource.
     *
     * @param weatherFetcher Fetcher that grabs the latest weather.
     */
    public MirrorResource(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    @GET
    @Path("/schedule")
    public ImmutableList<ScheduleDay> getSchedule() {
        return ImmutableList.of(
                ScheduleDay.newBuilder()
                        .withDate(LocalDate.of(2017, 7, 3))
                        .build(),
                ScheduleDay.newBuilder()
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
                        .build());
    }

    @GET
    @Path("/weather")
    public Weather getWeather() throws Exception {
        return weatherCache.get("weather", weatherFetcher::currentWeather);
    }
}
