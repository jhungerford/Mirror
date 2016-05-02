package dev.mirror.services;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import dev.mirror.views.Weather;
import dev.mirror.views.WeatherData;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

// TODO: cache weather responses - forecast.io has a limit of 1000 requests / day
public class WeatherService {

    private final WeatherFetcher weatherFetcher;

    public WeatherService(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public Weather getWeather() {
        String json = weatherFetcher.fetchWeatherJson();
        return parseJson(json);
    }

    public Weather parseJson(String jsonStr) {
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
    }
}
