import React, { Component } from 'react';
import './css/Weather.css';

class Weather extends Component {

  render() {
    return (
      <section className="weather">
        <div className="title"><i className="wi wi-forecast-io-clear-day">&nbsp;</i> 80° Clear</div>
        <div className="row row-weather">
          <div className="column column-weather-icon"><i className="wi wi-forecast-io-rain">&nbsp;</i></div>
          <div className="column column-weather-day">Today</div>
          <div className="column column-weather-range-min" style={{'margin-left': 0, width: '14%'}}>61°</div>
          <div className="column column-weather-range-bar" style={{width: '37%'}}>&nbsp;</div>
          <div className="column column-weather-range-max" style={{width: '24%'}}>90°</div>
        </div>
        <div className="row row-weather">
          <div className="column column-weather-icon"><i className="wi wi-forecast-io-partly-cloudy-day">&nbsp;</i></div>
          <div className="column column-weather-day">Tue</div>
          <div className="column column-weather-range-min" style={{'margin-left': '5%', width:'14%'}}>66°</div>
          <div className="column column-weather-range-bar" style={{width: '32%'}}>&nbsp;</div>
          <div className="column column-weather-range-max" style={{width: '24%'}}>89°</div>
        </div>
        <div className="row row-weather">
          <div className="column column-weather-icon"><i className="wi wi-forecast-io-partly-cloudy-day">&nbsp;</i></div>
          <div className="column column-weather-day">Wed</div>
          <div className="column column-weather-range-min" style={{'margin-left': '5%', width: '14%'}}>65°</div>
          <div className="column column-weather-range-bar" style={{width: '42%'}}>&nbsp;</div>
          <div className="column column-weather-range-max" style={{width: '14%'}}>96°</div>
        </div>
        <div className="weather-link">
          <a href="https://darksky.net/poweredby/">Powered by Dark Sky</a>
        </div>
      </section>
    );
  }
}

export default Weather;
