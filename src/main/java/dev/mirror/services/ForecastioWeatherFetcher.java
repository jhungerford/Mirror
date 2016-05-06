package dev.mirror.services;

import io.dropwizard.lifecycle.Managed;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class ForecastioWeatherFetcher implements WeatherFetcher, Managed {
    private static final Logger log = getLogger(ForecastioWeatherFetcher.class);

    private final String url;
    private CloseableHttpClient httpClient;


    public ForecastioWeatherFetcher(String apiKey, String latitude, String longitude) {
        this.url = buildUrl(apiKey, latitude, longitude);
    }

    @Override
    public void start() throws Exception {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        } else {
            throw new IllegalStateException("ForecastioWeatherFetcher is already running");
        }
    }

    @Override
    public void stop() throws Exception {
        if (httpClient != null) {
            httpClient.close();
            httpClient = null;
        }
    }

    @Override
    public String fetchWeatherJson() throws IOException {
        if (httpClient == null) {
            throw new IOException("Weather fetcher must be started.");
        }

        HttpGet get = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(get)) {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.warn("Error getting weather from forecast.io: {}", response.getStatusLine());
                throw new IOException("Unable to retrieve weather");
            }

            return EntityUtils.toString(response.getEntity());
        }
    }

    public static String buildUrl(String apiKey, String latitude, String longitude) {
        return String.format("https://api.forecast.io/forecast/%s/%s,%s", apiKey, latitude, longitude);
    }
}
