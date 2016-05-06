package dev.mirror.services;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastioWeatherFetcherTest {

    @Test
    public void urlBuiltCorrectly() {

        assertThat(ForecastioWeatherFetcher.buildUrl("api-key", "37.8267", "-122.423"))
                .isEqualTo("https://api.forecast.io/forecast/api-key/37.8267,-122.423");
    }

    @Test
    public void weatherFetched() throws Exception {
        String apiKey = System.getenv("MIRROR_FORECASTIO_API_KEY");
        assertThat(apiKey).withFailMessage("Set the 'MIRROR_FORECASTIO_API_KEY' environment variable").isNotEmpty();

        ForecastioWeatherFetcher fetcher = new ForecastioWeatherFetcher(apiKey, "37.8267", "-122.423");
        fetcher.start();
        String jsonStr = fetcher.fetchWeatherJson();

        assertThat(jsonStr).withFailMessage("Received empty response from forecastio").isNotEmpty();

        DocumentContext json = JsonPath.parse(jsonStr);
        assertThat(json.read("$.latitude", String.class)).isEqualTo("37.8267");
        assertThat(json.read("$.longitude", String.class)).isEqualTo("-122.423");

        fetcher.stop();
    }
}