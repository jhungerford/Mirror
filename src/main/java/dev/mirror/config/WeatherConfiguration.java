package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mirror.services.ForecastioWeatherFetcher;
import dev.mirror.services.WeatherService;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class WeatherConfiguration {

    @NotEmpty
    private String forecastioApiKey;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

    @JsonProperty
    public String getForecastioApiKey() {
        return forecastioApiKey;
    }

    @JsonProperty
    public void setForecastioApiKey(String forecastioApiKey) {
        this.forecastioApiKey = forecastioApiKey;
    }

    @JsonProperty
    public double getLatitude() {
        return latitude;
    }

    @JsonProperty
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public WeatherService buildService() {
        ForecastioWeatherFetcher weatherFetcher = new ForecastioWeatherFetcher(forecastioApiKey, latitude, longitude);
        return new WeatherService(weatherFetcher);
    }
}
