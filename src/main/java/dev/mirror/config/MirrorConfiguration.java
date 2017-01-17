package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class MirrorConfiguration extends Configuration {
    @Valid
    @NotNull
    private WeatherFactory weatherFactory = new WeatherFactory();

    @Valid
    @NotNull
    private RadarFactory radarFactory = new RadarFactory();

    @JsonProperty("weather")
    public WeatherFactory getWeatherFactory() {
        return weatherFactory;
    }

    @JsonProperty("weather")
    public void setWeatherFactory(WeatherFactory weatherFactory) {
        this.weatherFactory = weatherFactory;
    }

    @JsonProperty("radar")
    public RadarFactory getRadarFactory() {
        return radarFactory;
    }

    @JsonProperty("radar")
    public void setRadarFactory(RadarFactory radarFactory) {
        this.radarFactory = radarFactory;
    }
}
