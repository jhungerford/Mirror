import React, { Component, PropTypes } from 'react';
import WeatherDay from './WeatherDay'
import Loading from './Loading'

function Repeat(props) {
  let items = [];
  for (let i = 0; i < props.count; i ++) {
    items.push(props.children(i));
  }
  return <div className="row">{items}</div>;
}

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
      weather = <Repeat count={ this.props.days }>
        { (index) => (
            <WeatherDay
                key={ index }
                day={ this.state.weatherInfo[index].day }
                high={ this.state.weatherInfo[index].high }
                low={ this.state.weatherInfo[index].low }
                icon={ this.state.weatherInfo[index].icon }
            />
        )}
      </Repeat>;
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
