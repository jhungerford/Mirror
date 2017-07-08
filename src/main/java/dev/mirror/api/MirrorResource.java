package dev.mirror.api;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import dev.mirror.model.ScheduleDay;
import dev.mirror.model.Weather;
import dev.mirror.fetcher.WeatherFetcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
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
        throw new IllegalStateException("Not Implemented"); // TODO: implement
    }

    @GET
    @Path("/weather")
    public Weather getWeather() throws Exception {
        return weatherCache.get("weather", weatherFetcher::currentWeather);
    }
}
