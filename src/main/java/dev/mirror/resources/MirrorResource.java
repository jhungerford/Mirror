package dev.mirror.resources;

import dev.mirror.views.MirrorView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MirrorResource {

    @GET
    public MirrorView root() {
        return index();
    }

    @GET
    @Path("index.html")
    public MirrorView index() {
        return new MirrorView();
    }
}
