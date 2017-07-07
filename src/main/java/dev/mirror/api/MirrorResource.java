package dev.mirror.api;

import com.google.common.collect.ImmutableList;
import dev.mirror.model.CurrentWeather;
import dev.mirror.model.ForecastDay;
import dev.mirror.model.ScheduleDay;
import dev.mirror.model.Weather;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/v1/mirror")
@Produces(MediaType.APPLICATION_JSON)
public class MirrorResource {

    @GET
    @Path("/schedule")
    public ImmutableList<ScheduleDay> getSchedule() {
        throw new IllegalStateException("Not Implemented"); // TODO: implement
    }

    @GET
    @Path("/weather")
    public Weather getWeather() {
        return Weather.newBuilder()
                .withCurrent(CurrentWeather.newBuilder()
                        .withIcon("clear-day")
                        .withTemperature(new BigDecimal(81))
                        .withSummary("Clear")
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499151600L)
                        .withDay("Today")
                        .withIcon("rain")
                        .withHigh(new BigDecimal(90))
                        .withLow(new BigDecimal(61))
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499238000L)
                        .withDay("Tue")
                        .withIcon("partly-cloudy-day")
                        .withHigh(new BigDecimal(89))
                        .withLow(new BigDecimal(66))
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499324400L)
                        .withDay("Wed")
                        .withIcon("partly-cloudy-day")
                        .withHigh(new BigDecimal(96))
                        .withLow(new BigDecimal(65))
                        .build())
                .build();
    }
}
