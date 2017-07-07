package dev.mirror.api;

import com.google.common.collect.ImmutableList;
import dev.mirror.model.ScheduleDay;
import dev.mirror.model.Weather;
import dev.mirror.fetcher.WeatherFetcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/mirror")
@Produces(MediaType.APPLICATION_JSON)
public class MirrorResource {

    private final WeatherFetcher weatherFetcher;

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
        throw new IllegalStateException("Not Implemented"); // TODO: implement
    }

    @GET
    @Path("/weather")
    public Weather getWeather() {
        // TODO: cache the weather.
        return weatherFetcher.currentWeather();
    }
}
