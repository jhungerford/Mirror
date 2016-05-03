package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mirror.config.weather.DefaultWeatherFactory;
import dev.mirror.config.weather.WeatherFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MirrorConfiguration extends Configuration {
    @Valid
    @NotNull
    private WeatherFactory weatherFactory = new DefaultWeatherFactory();

    @JsonProperty("weather")
    public WeatherFactory getWeatherFactory() {
        return weatherFactory;
    }

    @JsonProperty("weather")
    public void setWeatherFactory(WeatherFactory weatherFactory) {
        this.weatherFactory = weatherFactory;
    }
}
