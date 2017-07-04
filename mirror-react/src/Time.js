import React, { Component } from 'react';
import './css/Time.css';

class Time extends Component {

  static padNumber(num) {
    return num < 10 ? '0' + num : num.toString()
  };

  render() {
    return (
      <div className="time">{ this.props.date.getHours() }:{ Time.padNumber(this.props.date.getMinutes()) }</div>
    );
  }
}

export default Time;
