import React, { Component, PropTypes } from 'react';

class WeatherDay extends Component {
  static propTypes = {
    day: PropTypes.string.isRequired,
    high: PropTypes.number.isRequired,
    low: PropTypes.number.isRequired,
    icon: PropTypes.string.isRequired
  };

  render() {
    return (
        <div className="row">
          <div className="column column-3">
            <i className="wi wi-day-{ icon }"></i>
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
