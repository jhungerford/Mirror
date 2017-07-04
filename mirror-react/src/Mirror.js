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
    return (
      <div className="Mirror row">
        <div className="column column-main">
          <Calendar />
          <Schedule />
          &nbsp;
        </div>
        <div className="column column-main">
          <Time date={ this.state.date }/>
        </div>
        <div className="column column-main">
          <Weather />
          &nbsp;
        </div>
      </div>
    );
  }
}

export default Mirror;
