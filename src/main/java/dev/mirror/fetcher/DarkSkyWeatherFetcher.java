package dev.mirror.fetcher;

import com.google.common.collect.ImmutableList;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import dev.mirror.model.CurrentWeather;
import dev.mirror.model.ForecastDay;
import dev.mirror.model.Weather;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.stream.IntStream;

import static org.slf4j.LoggerFactory.getLogger;

public class DarkSkyWeatherFetcher implements WeatherFetcher, Managed {
    private static final Logger log = getLogger(DarkSkyWeatherFetcher.class);

    // TODO: day names are a presentation concern, so weather should just have the day of the week.
    private static final String[] DAY_NAMES = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    private final Client client;
    private final WebTarget webTarget;
    private final int days;

    public DarkSkyWeatherFetcher(String apiKey, String latitude, String longitude, int days) {
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(buildUrl(apiKey, latitude, longitude));
        this.days = days;
    }

    @Override
    public Weather currentWeather() {
        return parseWeatherJson(fetchWeatherJson(), days);
    }

    /**
     * Fetches weather json from the dark sky API using the configured API key and location
     *
     * @return Current weather json.
     */
    public String fetchWeatherJson() {
        return webTarget.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
    }

    /**
     * Parses the given weather response json into a Weather object.  The forecast section will
     * contain the given number of days, including today.
     *
     * @param jsonStr Json response from the darksky api
     * @param days    Number of days to include in the forecast section
     * @return Parsed weather
     */
    public static Weather parseWeatherJson(String jsonStr, int days) {
        try {
            DocumentContext json = JsonPath.parse(jsonStr);

            ZoneId localZone = ZoneId.of(json.read("$.timezone"));

            return Weather.newBuilder()
                    .withCurrent(CurrentWeather.newBuilder()
                            .withIcon(json.read("$.currently.icon"))
                            .withTemperature(json.read("$.currently.temperature", BigDecimal.class))
                            .withSummary(json.read("$.currently.summary"))
                            .build())
                    .withForecast(IntStream
                            .range(0, days)
                            .mapToObj(index -> {
                                String dayPath = "$.daily.data[" + index + "]";
                                String dayName = index == 0
                                        ? "Today"
                                        : DAY_NAMES[
                                        Instant.ofEpochSecond(json.read(dayPath + ".time", Long.class))
                                                .atZone(localZone)
                                                .get(ChronoField.DAY_OF_WEEK) - 1];

                                return ForecastDay.newBuilder()
                                        .withTime(json.read(dayPath + ".time", Long.class))
                                        .withDay(dayName)
                                        .withIcon(json.read(dayPath + ".icon"))
                                        .withLow(json.read(dayPath + ".temperatureMin", BigDecimal.class))
                                        .withHigh(json.read(dayPath + ".temperatureMax", BigDecimal.class))
                                        .build();
                            })
                            .collect(ImmutableList.toImmutableList()))
                    .build();
        } catch (JsonPathException ex) {
            log.error("Error parsing weather json: '" + jsonStr + "'", ex);
            throw new RuntimeException("Error parsing weather json", ex);
        }
    }

    @Override
    public void start() throws Exception {
        // Nothing to start.
    }

    @Override
    public void stop() throws Exception {
        this.client.close();
    }

    /**
     * Builds a URL that will fetch current weather data from the darksky api using the given
     * API key and location.
     *
     * @param apiKey    API key registered with darksky
     * @param latitude  Location latitude
     * @param longitude Location longitude
     * @return URL that will fetch weather data
     */
    public static String buildUrl(String apiKey, String latitude, String longitude) {
        return String.format("https://api.darksky.net/forecast/%s/%s,%s", apiKey, latitude, longitude);
    }
}
