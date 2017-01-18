import React, { Component, PropTypes } from 'react';

import './css/weather-icons.css'

class WeatherDay extends Component {
  static propTypes = {
    day: PropTypes.string.isRequired,
    high: PropTypes.number.isRequired,
    low: PropTypes.number.isRequired,
    icon: PropTypes.string.isRequired
  };

  render() {
    const icon = 'wi wi-day-' + this.props.icon;

    return (
        <div className="row">
          <div className="column column-3">
            <i className={ icon }></i>
            { this.props.day }
          </div>
          <div className="column column-6">
            { this.props.high }˚/{ this.props.low }˚
          </div>
          <div className="column column-3">
            0 in
          </div>
        </div>
    );
  }
}

export default WeatherDay;
