<#-- @ftlvariable name="time" type="org.joda.time.DateTime" -->
<#-- @ftlvariable name="weather" type="dev.mirror.views.Weather" -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Mirror</title>

    <link href="/css/weather-icons.css" rel="stylesheet" />
    <link href="/css/mirror.css" rel="stylesheet" />
</head>
<body>
<div class="grid">
    <div class="column">
        &nbsp;
    </div>
    <div class="column">
        <section class="clock">
            <h1 class="clock-time">${time.toString("H:mm")}</h1>
            <h3 class="clock-date">${time.toString("EEEE, MMMM d, yyyy")}</h3>
        </section>
    </div>
    <div class="column">
        <section class="weather">
            <h1 class="weather-current"><i class="wi wi-forecast-io-${weather.current.icon}"></i> ${weather.current.temperature}°</h1>
            <h3>${weather.current.summary} - feels like ${weather.current.apparentTemperature}.</h3>
            <h3>${weather.hourlySummary}</h3>
            <!-- Forecast.io style table: TODAY <icon> Partly cloudly throughout the day. 40 ===== 60 -->
            <!-- low 40 at 5am, high 60 at 5PM, sunrise 5:50 AM, sunset 8:07 PM -->
        </section>
    </div>
</div>
</body>
</html>
