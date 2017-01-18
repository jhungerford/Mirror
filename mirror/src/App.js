import React, { Component } from 'react';
import './css/App.css';
import Calendar from './Calendar';
import Clock from './Clock';
import Weather from './Weather';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="row">
          <div className="column column-4">
            <Calendar />
          </div>
          <div className="column column-4">
            <Clock />
          </div>
          <div className="column column-4">
            <div className="row">
              <Weather city="Boulder" days={ 3 } />
            </div>
            <div className="row">
              <Weather city="Vail" days={ 1 } />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
