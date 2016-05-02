package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MirrorConfiguration extends Configuration {
    @Valid
    @NotNull
    private WeatherConfiguration weather;

    @JsonProperty
    public WeatherConfiguration getWeather() {
        return weather;
    }

    @JsonProperty
    public void setWeather(WeatherConfiguration weather) {
        this.weather = weather;
    }
}
