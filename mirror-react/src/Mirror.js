import React, { Component } from 'react';

import Calendar from './Calendar';
import Schedule from './Schedule';
import Time from './Time';
import Weather from './Weather';
import Loader from './Loader'

import './css/grid.css';
import './css/Mirror.css';

class Mirror extends Component {

  constructor(props) {
    super(props);
    this.state = {
      weather: this.props.weather,
      date: new Date()
    };
  }

  componentDidMount() {
    this.intervalId = setInterval(() => this.tick(), 1000);

    if (! this.state.weather) {
      fetch('/api/v1/mirror/weather')
        .then(response => response.json())
        .then(weather => this.setState({weather: weather}))
    }
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
    const mockupSchedule = [
      {
        date: '2017-07-03',
        events: []
      }, {
        date: '2017-07-04',
        events: [
          {
            id: '1',
            description: 'Independence Day'
          },{
            id: '2',
            time: '2017-07-04T14:30:00.000Z',
            description: 'Coffee'
          },{
            id: '3',
            time: '2017-07-04T16:30:00.000Z',
            description: 'Engineering Leads Daily'
          },{
            id: '4',
            time: '2017-07-04T17:00:00.000Z',
            description: 'Standup'
          },{
            id: '5',
            time: '2017-07-04T19:00:00.000Z',
            description: 'Event with a name that\'s too long to fit in a row comfortably.'
          }
        ]
      }
    ];

    return (
      <div className="Mirror row">
        <div className="column column-main">
          <Calendar date={ this.state.date } />
          <Schedule schedule={ mockupSchedule } />
        </div>
        <div className="column column-main">
          <Time date={ this.state.date } />
        </div>
        <div className="column column-main">
          { this.state.weather
            ? <Weather data={ this.state.weather } />
            : <Loader />
          }
        </div>
      </div>
    );
  }
}

export default Mirror;
