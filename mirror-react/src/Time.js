import React, { Component } from 'react';
import './css/Time.css';

class Time extends Component {

  static now = () => new Date();
  
  static timeFields(date) {
    return {
      hour: date.getHours(),
      minute: date.getMinutes()
    };
  }

  static padNumber(num) {
    return num < 10 ? '0' + num : num.toString()
  };

  constructor(props) {
    super(props);

    this.state = Time.timeFields(Time.now());
  }

  componentDidMount() {
    const intervalId = setInterval(this.tick, 1000);
    this.setState({ intervalId: intervalId });
  }

  componentWillUnmount() {
    clearInterval(this.state.intervalId);
  }

  tick = () => this.setState(Time.timeFields(Time.now()));

  render() {
    return (
      <div className="time">{ this.state.hour }:{ Time.padNumber(this.state.minute) }</div>
    );
  }
}

export default Time;
