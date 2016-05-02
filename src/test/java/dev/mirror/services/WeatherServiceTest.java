package dev.mirror.services;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import dev.mirror.views.Weather;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherServiceTest {

    private WeatherFetcher mockFetcher = Mockito.mock(WeatherFetcher.class);

    private WeatherService weatherService = new WeatherService(mockFetcher);

    @Test
    @Ignore
    public void parseSampleResponse() throws Exception {
        String json = Files.toString(new File(Resources.getResource("sample-forecastio-response.json").toURI()), Charsets.UTF_8);
        Weather weather = weatherService.parseJson(json);

        assertThat(weather).isNotNull();

        assertThat(weather.getCurrent()).isNotNull();
        assertThat(weather.getCurrent().getTime()).isEqualTo(new DateTime(1462033271000L, DateTimeZone.forID("America/Los_Angeles")));
        assertThat(weather.getCurrent().getSummary()).isEqualTo("Clear");
        assertThat(weather.getCurrent().getIcon()).isEqualTo("clear-day");
        assertThat(weather.getCurrent().getTemperature()).isEqualTo(62.01);
        assertThat(weather.getCurrent().getApparentTemperature()).isEqualTo(62.01);

        assertThat(weather.getMinutelySummary()).isEqualTo("Clear for the hour.");
        assertThat(weather.getHourlySummary()).isEqualTo("Clear throughout the day.");
        assertThat(weather.getDailySummary()).isEqualTo("Drizzle on Wednesday, with temperatures falling to 60° on Wednesday.");
    }
}