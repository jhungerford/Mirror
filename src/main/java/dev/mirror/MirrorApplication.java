package dev.mirror;

import dev.mirror.api.MirrorResource;
import dev.mirror.config.MirrorConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MirrorApplication extends Application<MirrorConfiguration> {

    @Override
    public void initialize(Bootstrap<MirrorConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/web", "/", "index.html", "web"));
    }

    public void run(MirrorConfiguration config, Environment env) throws Exception {
        env.jersey().register(new MirrorResource());
    }
}
