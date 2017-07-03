import React, { Component } from 'react';
import './css/Schedule.css';

class Schedule extends Component {

  render() {
    return (
      <section className="schedule">
        <div className="schedule-day">
          <div className="title">Monday, July 3</div>
          <div className="row">
            <div className="column column-schedule-all">No More Events</div>
          </div>
        </div>
        <div className="schedule-day">
          <div className="title">Tues, July 4</div>
          <div className="row">
            <div className="column column-schedule-all">Independence Day</div>
          </div>
          <div className="row">
            <div className="column column-schedule-time">8:30</div>
            <div className="column column-schedule-event">Coffee</div>
          </div>
          <div className="row">
            <div className="column column-schedule-time">10:30</div>
            <div className="column column-schedule-event">Engineering Leads Daily</div>
          </div>
          <div className="row">
            <div className="column column-schedule-time">11:00</div>
            <div className="column column-schedule-event">Standup</div>
          </div>
          <div className="row">
            <div className="column column-schedule-time">13:00</div>
            <div className="column column-schedule-event">Event with a name that's too long to fit in a row comfortably.</div>
          </div>
        </div>
      </section>
    );
  }
}

export default Schedule;
