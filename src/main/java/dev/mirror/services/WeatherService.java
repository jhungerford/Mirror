package dev.mirror.services;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import dev.mirror.views.Weather;
import dev.mirror.views.WeatherData;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

// TODO: cache weather responses - forecast.io has a limit of 1000 requests / day
public class WeatherService {
    private static final Logger log = getLogger(WeatherService.class);

    private final WeatherFetcher weatherFetcher;

    public WeatherService(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public Weather getWeather() throws IOException {
        String json = weatherFetcher.fetchWeatherJson();
        return parseJson(json);
    }

    public Weather parseJson(String jsonStr) throws IOException {
        try {
            DocumentContext json = JsonPath.parse(jsonStr);

            DateTimeZone timezone = DateTimeZone.forID(json.read("$.timezone", String.class));

            WeatherData current = new WeatherData();
            current.setTime(new DateTime(json.read("$.currently.time", Long.class) * 1000, timezone));
            current.setSummary(json.read("$.currently.summary", String.class));
            current.setIcon(json.read("$.currently.icon", String.class));
            current.setTemperature(json.read("$.currently.temperature", Double.class));
            current.setApparentTemperature(json.read("$.currently.apparentTemperature", Double.class));

            Weather weather = new Weather();
            weather.setCurrent(current);
            weather.setMinutelySummary(json.read("$.minutely.summary", String.class));
            weather.setHourlySummary(json.read("$.hourly.summary", String.class));
            weather.setDailySummary(json.read("$.daily.summary", String.class));

            return weather;
        } catch (JsonPathException e) {
            log.error("Error parsing weather json: '" + jsonStr + "'", e);
            throw new IOException("Error parsing weather json", e);
        }
    }
}
