import React from 'react';
import './css/Weather.css';

function CurrentWeather(props) {
  return <div className="title">
    <i className={ "wi wi-forecast-io-" + props.icon }>&nbsp;</i>
    { Math.round(props.temperature) }° { props.summary }
  </div>
}

function DayWeather(props) {
  // Minimum label width is 14%, and the min, bar, and high column widths should add up to 75%,
  // leaving 47% for the widest bar.
  const range = props.range.high - props.range.low;
  const minWidth = Math.round(14 + 47 * (props.low - props.range.low) / range);
  const barWidth = Math.round(47 * (props.high - props.low) / range);
  const maxWidth = 75 - minWidth - barWidth;

  return <div className="row row-weather">
    <div className="column column-weather-icon"><i className={ "wi wi-forecast-io-" + props.icon }>&nbsp;</i></div>
    <div className="column column-weather-day">{ props.day }</div>
    <div className="column column-weather-range-min" style={{width: minWidth + '%'}}>{ Math.round(props.low) }°</div>
    <div className="column column-weather-range-bar" style={{width: barWidth + '%'}}>&nbsp;</div>
    <div className="column column-weather-range-max" style={{width: maxWidth + '%'}}>{ Math.round(props.high) }°</div>
  </div>
}

function DarkSkyLink(props) {
  return <div className="weather-link">
    <a href="https://darksky.net/poweredby/">Powered by Dark Sky</a>
  </div>
}

function Weather(props) {
  const forecastRange = {
    high: props.data.forecast.reduce((highSoFar, day) => day.high > highSoFar ? day.high : highSoFar, Number.MIN_VALUE),
    low: props.data.forecast.reduce((lowSoFar, day) => day.low < lowSoFar ? day.low : lowSoFar, Number.MAX_VALUE)
  };

  return (
    <section className="weather">
      <CurrentWeather { ...props.data.current } />

      { props.data.forecast.map(day =>
        <DayWeather key={ day.time } range={ forecastRange } { ...day } />
      )}

      <DarkSkyLink />
    </section>
  );
}

export default Weather;
