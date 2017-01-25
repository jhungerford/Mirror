import React, { Component, PropTypes } from 'react'
import WeatherDay from './WeatherDay'
import Loading from './Loading'
import '../css/Weather.css'

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
        precipitation: 0,
        icon: 'sunny',
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
            <WeatherDay key={ index }
                day={ info.day }
                high={ info.high }
                low={ info.low }
                precipitation={ info.precipitation }
                icon={ info.icon }
            />
        ));
    }

    return (
        <div className="weather">
          <div className="city">{ this.props.city }</div>
          { weather }
        </div>
    )
  }
}

export default Weather;
