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
        &nbsp;
    </div>
</div>
</body>
</html>