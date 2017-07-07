package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MirrorConfiguration extends Configuration {

    @Valid
    @NotNull
    private WeatherFactory weatherFactory = new WeatherFactory();

    @JsonProperty("weather")
    public WeatherFactory getWeatherFactory() {
        return weatherFactory;
    }

    @JsonProperty("weather")
    public void setWeatherFactory(WeatherFactory weatherFactory) {
        this.weatherFactory = weatherFactory;
    }
}
