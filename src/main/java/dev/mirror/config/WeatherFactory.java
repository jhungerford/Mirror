package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mirror.services.ForecastioWeatherFetcher;
import dev.mirror.services.WeatherService;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class WeatherFactory {

    @NotEmpty
    private String forecastioApiKey;

    @NotEmpty
    private String latitude;

    @NotEmpty
    private String longitude;

    @JsonProperty
    public String getForecastioApiKey() {
        return forecastioApiKey;
    }

    @JsonProperty
    public void setForecastioApiKey(String forecastioApiKey) {
        this.forecastioApiKey = forecastioApiKey;
    }

    @JsonProperty
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonProperty
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public WeatherService build(Environment environment) throws Exception {
        ForecastioWeatherFetcher weatherFetcher = new ForecastioWeatherFetcher(forecastioApiKey, latitude, longitude);
        environment.lifecycle().manage(weatherFetcher);

        return new WeatherService(weatherFetcher);
    }
}
