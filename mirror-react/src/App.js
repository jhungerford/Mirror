import React, { Component } from 'react';

import Calendar from './Calendar';
import Schedule from './Schedule';
import Time from './Time';
import Weather from './Weather';

import './css/grid.css';
import './css/App.css';

class App extends Component {
  render() {
    return (
      <div className="App row">
        <div className="column column-main">
          <Calendar />
          <Schedule />
          &nbsp;
        </div>
        <div className="column column-main">
          <Time />
        </div>
        <div className="column column-main">
          <Weather />
          &nbsp;
        </div>
      </div>
    );
  }
}

export default App;
