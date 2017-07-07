package dev.mirror.fetcher;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import dev.mirror.model.CurrentWeather;
import dev.mirror.model.ForecastDay;
import dev.mirror.model.Weather;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DarkSkyWeatherFetcherTest {

    @Test
    public void urlBuiltCorrectly() {
        assertThat(DarkSkyWeatherFetcher.buildUrl("api-key", "37.8267", "-122.423"))
                .isEqualTo("https://api.darksky.net/forecast/api-key/37.8267,-122.423");
    }

    @Test
    public void weatherFetched() throws Exception {
        String apiKey = System.getenv("MIRROR_DARKSKY_API_KEY");
        assertThat(apiKey).withFailMessage("Set the 'MIRROR_DARKSKY_API_KEY' environment variable").isNotEmpty();

        DarkSkyWeatherFetcher fetcher = new DarkSkyWeatherFetcher(apiKey, "37.8267", "-122.423", 3);
        fetcher.start();

        String jsonStr = fetcher.fetchWeatherJson();

        assertThat(jsonStr).withFailMessage("Received empty response from dark sky").isNotEmpty();

        DocumentContext json = JsonPath.parse(jsonStr);
        assertThat(json.read("$.latitude", String.class)).isEqualTo("37.8267");
        assertThat(json.read("$.longitude", String.class)).isEqualTo("-122.423");

        fetcher.stop();
    }

    @Test
    public void weatherJsonParsed() throws Exception {
        String json = Files.toString(new File(Resources.getResource("weather-sample.json").toURI()), Charsets.UTF_8);
        Weather actualWeather = DarkSkyWeatherFetcher.parseWeatherJson(json, 3);

        Weather expectedWeather = Weather.newBuilder()
                .withCurrent(CurrentWeather.newBuilder()
                        .withIcon("partly-cloudy-day")
                        .withTemperature(new BigDecimal("63.38"))
                        .withSummary("Partly Cloudy")
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499151600L)
                        .withDay("Today")
                        .withIcon("partly-cloudy-day")
                        .withLow(new BigDecimal("51.6"))
                        .withHigh(new BigDecimal("64.7"))
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499238000L)
                        .withDay("Wed")
                        .withIcon("partly-cloudy-day")
                        .withLow(new BigDecimal("52.65"))
                        .withHigh(new BigDecimal("62.93"))
                        .build())
                .addForecastDay(ForecastDay.newBuilder()
                        .withTime(1499324400L)
                        .withDay("Thu")
                        .withIcon("partly-cloudy-day")
                        .withLow(new BigDecimal("51.05"))
                        .withHigh(new BigDecimal("66.56"))
                        .build())
                .build();

        assertThat(actualWeather).isEqualTo(expectedWeather);
    }
}