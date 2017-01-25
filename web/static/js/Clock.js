import React, { Component } from 'react';
import '../css/Clock.css';

const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

class Clock extends Component {

  static padNumber(num) {
    return num < 10 ? '0' + num : num.toString()
  };

  static now = () => new Date();

  static dateFields(date) {
    return {
      day: days[date.getDay()],
      date: date.getDate(),
      month: months[date.getMonth()],
      year: date.getFullYear(),
      hour: date.getHours(),
      minute: Clock.padNumber(date.getMinutes()),
      second: Clock.padNumber(date.getSeconds())
    }
  }

  constructor(props) {
    super(props);
    this.state = Clock.dateFields(Clock.now());
  }

  componentDidMount() {
    const intervalId = setInterval(this.tick, 1000);
    this.setState({ intervalId: intervalId });
  }

  componentWillUnmount() {
    clearInterval(this.state.intervalId);
  }

  tick = () => this.setState(Clock.dateFields(Clock.now()));

  render() {
    return (
        <div className="clock">
          <div className="clock-time">{ this.state.hour }:{ this.state.minute }:{ this.state.second }</div>
          <div className="clock-date">{ this.state.day }, { this.state.month } { this.state.date }, { this.state.year }</div>
        </div>
    );
  }
}

export default Clock;
