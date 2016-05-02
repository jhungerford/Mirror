package dev.mirror.resources;

import dev.mirror.services.WeatherService;
import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MirrorResource {

    private final WeatherService weatherService;

    public MirrorResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    public MirrorView root() {
        return buildMirrorView();
    }

    @GET
    @Path("index.html")
    public MirrorView index() {
        return buildMirrorView();
    }

    public MirrorView buildMirrorView() {
        return MirrorView.builder()
                .setTime(DateTime.now())
                .build();
    }
}
