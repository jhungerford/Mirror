package dev.mirror.views;

public class Weather {

    private WeatherData current;

    private String minutelySummary;
    private String hourlySummary;
    private String dailySummary;

    public WeatherData getCurrent() {
        return current;
    }

    public void setCurrent(WeatherData current) {
        this.current = current;
    }

    public String getMinutelySummary() {
        return minutelySummary;
    }

    public void setMinutelySummary(String minutelySummary) {
        this.minutelySummary = minutelySummary;
    }

    public String getHourlySummary() {
        return hourlySummary;
    }

    public void setHourlySummary(String hourlySummary) {
        this.hourlySummary = hourlySummary;
    }

    public String getDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(String dailySummary) {
        this.dailySummary = dailySummary;
    }
}
