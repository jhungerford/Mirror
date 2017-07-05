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
          <Weather data={ mockupWeather } />
        </div>
      </div>
    );
  }
}

export default Mirror;
