package dev.mirror;

import dev.mirror.config.MirrorConfiguration;
import dev.mirror.resources.MirrorResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class MirrorApplication extends Application<MirrorConfiguration> {

    @Override
    public void initialize(Bootstrap<MirrorConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<MirrorConfiguration>());
    }

    public void run(MirrorConfiguration config, Environment env) throws Exception {
        env.jersey().register(new MirrorResource());
    }
}
