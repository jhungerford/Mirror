import React, { Component, PropTypes } from 'react'

import '../css/weather-icons.css'
import '../css/WeatherDay.css'

class WeatherDay extends Component {
  static propTypes = {
    day: PropTypes.string.isRequired,
    high: PropTypes.number.isRequired,
    low: PropTypes.number.isRequired,
    precipitation: PropTypes.number.isRequired,
    icon: PropTypes.string.isRequired
  };

  render() {
    const icon = 'weather-day-icon wi wi-day-' + this.props.icon;

    return (
        <div className="row weather-day">
          <div className="column column-3">
            <i className={ icon } /> { this.props.day }
          </div>
          <div className="column column-6">
            { this.props.high }˚/{ this.props.low }˚
          </div>
          <div className="column column-3">
            { this.props.precipitation } in
          </div>
        </div>
    );
  }
}

export default WeatherDay;
