package dev.mirror.config.weather;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.mirror.services.TestWeatherFetcher;
import dev.mirror.services.WeatherService;
import io.dropwizard.setup.Environment;

@JsonTypeName("test")
public class TestWeatherFactory implements WeatherFactory {

    @Override
    public WeatherService build(Environment environment) throws Exception {
        return new WeatherService(new TestWeatherFetcher());
    }
}
