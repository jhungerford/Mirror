package dev.mirror;

import dev.mirror.config.MirrorConfiguration;
import dev.mirror.health.MirrorHealthCheck;
import dev.mirror.resources.MirrorResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class MirrorApplication extends Application<MirrorConfiguration> {

    @Override
    public void initialize(Bootstrap<MirrorConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/css", "/css", "", "css"));
        bootstrap.addBundle(new AssetsBundle("/font", "/font", "", "font"));

        // Forecast.io api key is secret - pass it in via an environment variable to keep it out of github
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    public void run(MirrorConfiguration config, Environment env) throws Exception {
        MirrorResource mirrorResource = new MirrorResource(
                config.getWeatherFactory().build(env),
                config.getRadarFactory().build(env)
        );

        env.healthChecks().register("app", new MirrorHealthCheck(mirrorResource));
        env.jersey().register(mirrorResource);
    }
}
