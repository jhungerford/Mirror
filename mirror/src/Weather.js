import React, { Component, PropTypes } from 'react';
import WeatherDay from './WeatherDay'
import Loading from './Loading'

/**
 * Weather information for a city.  Displays a configurable number of days worth of weather information.
 */
class Weather extends Component {
  static propTypes = {
    city: PropTypes.string.isRequired,
    days: PropTypes.number.isRequired
  };

  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      weatherInfo: []
    }
  }

  componentDidMount() {
    let weatherInfo = new Array(this.props.days).fill({
        high: 32,
        low: 20,
        day: 'Tue',
        icon: 'snow'
      });


    this.setState({
      loading: false,
      weatherInfo: weatherInfo
    })
  }

  render() {
    let weather;
    if (this.state.loading) {
      weather = <Loading />;
    } else {
      weather = this.state.weatherInfo.map( (info, index) => (
            <div key={index} className="row">
              <WeatherDay
                  day={ info.day }
                  high={ info.high }
                  low={ info.low }
                  icon={ info.icon }
              />
            </div>
        ));
    }

    return (
        <div className="weather">
          <div className="row">
            <p>{ this.props.city }</p>
          </div>
          { weather }
        </div>
    );
  }
}

export default Weather;
