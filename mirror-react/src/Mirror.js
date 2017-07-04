import React, { Component } from 'react';

import Calendar from './Calendar';
import Schedule from './Schedule';
import Time from './Time';
import Weather from './Weather';

import './css/grid.css';
import './css/Mirror.css';

class Mirror extends Component {

  constructor(props) {
    super(props);
    this.state = {
      date: new Date()
    };
  }

  componentDidMount() {
    this.intervalId = setInterval(() => this.tick(), 1000);
  }

  componentWillUnmount() {
    clearInterval(this.intervalId);
  }

  tick() {
    this.setState({
      date: new Date()
    });
  }

  render() {
    const mockupWeather = {
      current: {
        icon: 'clear-day',
        temperature: 80,
        summary: 'Clear'
      },
      forecast: [
        {time: 1499151600, day: 'Today', icon: 'rain', high: 90, low: 61},
        {time: 1499238000, day: 'Tue', icon: 'partly-cloudy-day', high: 89, low: 66},
        {time: 1499324400, day: 'Wed', icon: 'partly-cloudy-day', high: 96, low: 65}
      ]
    };

    return (
      <div className="Mirror row">
        <div className="column column-main">
          <Calendar date={ this.state.date } />
          <Schedule />
        </div>
        <div className="column column-main">
          <Time date={ this.state.date }/>
        </div>
        <div className="column column-main">
          <Weather data={ mockupWeather } />
        </div>
      </div>
    );
  }
}

export default Mirror;
