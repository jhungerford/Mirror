import React, { Component } from 'react';

import Calendar from './Calendar';
import Clock from './Clock';
import Weather from './Weather';

import '../css/App.css';

class App extends Component {
  render() {
    return (
      <div className="app row">
        <div className="column column-4">
          <Calendar />
        </div>
        <div className="column column-4">
          <Clock />
        </div>
        <div className="column column-4">
          <Weather city="Boulder" days={ 3 } />
          <Weather city="Vail" days={ 1 } />
          <a className="licenseLink" href="https://darksky.net/dev/">Powered by Dark Sky</a>
        </div>
      </div>
    );
  }
}

export default App;
