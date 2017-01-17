package dev.mirror.resources;

import dev.mirror.services.RadarService;
import dev.mirror.services.WeatherService;
import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("/")
public class MirrorResource {

    private final WeatherService weatherService;
    private final RadarService radarService;

    public MirrorResource(WeatherService weatherService, RadarService radarService) {
        this.weatherService = weatherService;
        this.radarService = radarService;
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
                .withTime(DateTime.now())
                .withWeather(weatherService.getWeather())
                .withRadar(radarService.getRadar())
                .build();
    }
}
