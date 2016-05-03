package dev.mirror.config.weather;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.mirror.services.WeatherService;
import io.dropwizard.jackson.Discoverable;
import io.dropwizard.setup.Environment;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = DefaultWeatherFactory.class)
public interface WeatherFactory extends Discoverable {

    WeatherService build(Environment environment) throws Exception;
}
