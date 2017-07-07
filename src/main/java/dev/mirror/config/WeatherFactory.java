package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mirror.fetcher.DarkSkyWeatherFetcher;
import dev.mirror.fetcher.WeatherFetcher;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WeatherFactory {

    @NotEmpty
    private String apiKey;

    @NotEmpty
    private String latitude;

    @NotEmpty
    private String longitude;

    @Min(1)
    @Max(8)
    private int days;

    @JsonProperty
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    @JsonProperty
    public int getDays() {
        return days;
    }

    @JsonProperty
    public void setDays(int days) {
        this.days = days;
    }

    public WeatherFetcher build(Environment environment) throws Exception {
        DarkSkyWeatherFetcher weatherFetcher = new DarkSkyWeatherFetcher(apiKey, latitude, longitude, days);
        environment.lifecycle().manage(weatherFetcher);

        return weatherFetcher;
    }
}