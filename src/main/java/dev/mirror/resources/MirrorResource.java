package dev.mirror.resources;

import dev.mirror.services.WeatherService;
import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("/")
public class MirrorResource {

    private final WeatherService weatherService;

    public MirrorResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    public MirrorView root() throws IOException {
        return buildMirrorView();
    }

    @GET
    @Path("index.html")
    public MirrorView index() throws IOException {
        return buildMirrorView();
    }

    public MirrorView buildMirrorView() throws IOException {
        return MirrorView.builder()
                .setTime(DateTime.now())
                .setWeather(weatherService.getWeather())
                .build();
    }
}
