import React, { Component } from 'react';
import { FormattedDate, FormattedTime } from 'react-intl';

class Clock extends Component {

  constructor(props) {
    super(props);
    this.state = {
      date: new Date()
    }
  }

  componentDidMount() {
    const intervalId = setInterval(() => this.tick, 1000);
    this.setState({ intervalId: intervalId });
  }

  componentWillUnmount() {
    clearInterval(this.state.intervalId);
  }

  tick() {
    this.setState({date: new Date()});
  };

  render() {
    return (
        <div className="clock">
          <div className="clock-time"><FormattedTime value={ this.state.date } /></div>
          <div className="clock-date"><FormattedDate value={this.state.date} /></div>
        </div>
    )
  }
}

export default Clock;
