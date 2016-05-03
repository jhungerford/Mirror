package dev.mirror.services;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class TestWeatherFetcher implements WeatherFetcher {

    private final String json;

    public TestWeatherFetcher() throws URISyntaxException, IOException {
        this.json = Files.toString(new File(Resources.getResource("sample-forecastio-response.json").toURI()), Charsets.UTF_8);
    }

    @Override
    public String fetchWeatherJson() throws IOException {
        return json;
    }
}
