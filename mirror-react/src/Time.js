import React, { Component } from 'react';
import './css/Time.css';

class Time extends Component {

  static padNumber(num) {
    return num < 10 ? '0' + num : num.toString()
  };

  constructor(props) {
    super(props);
    this.state = {
      date: new Date()
    };
  }

  componentDidMount() {
    this.intervalId = setInterval(() => this.tick, 1000);
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
      <div className="time">{ this.state.date.getHours() }:{ Time.padNumber(this.state.date.getMinutes()) }</div>
    );
  }
}

export default Time;
