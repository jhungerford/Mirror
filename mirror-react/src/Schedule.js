import React, { Component } from 'react';
import './css/Schedule.css';

const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

function zeroPad(num) {
  return num < 10 ? '0' + num : num.toString()
};

function ScheduleDate(props) {
  const date = new Date(props.date);

  return <div className="title">{ days[date.getUTCDay()] }, { months[date.getUTCMonth()] } { date.getUTCDate() }</div>
}

function ScheduleTimeEvent(props) {
  const date = new Date(props.time);

  return (
    <div className="row">
      <div className="column column-schedule-time">{ date.getHours() }:{ zeroPad(date.getMinutes()) }</div>
      <div className="column column-schedule-event">{ props.description }</div>
    </div>
  );
}

function ScheduleAllDayEvent(props) {
  return (
    <div className="row">
      <div className="column column-schedule-all">{ props.description }</div>
    </div>
  );
}

function scheduleEvents(events) {
  if (events && events.length) {
    return events.map(event => event.time
      ? <ScheduleTimeEvent key={ event.id } time={ event.time } description={ event.description }/>
      : <ScheduleAllDayEvent key={ event.id } description={ event.description }/>
    );
  } else {
    return <ScheduleAllDayEvent description="No More Events" />
  }
}

function Schedule(props) {
  return <section className="schedule">
    { props.schedule.map(day =>
      <div key={ day.date } className="schedule-day">
        <ScheduleDate date={ day.date }/>
        { scheduleEvents(day.events) }
      </div>
    )}
  </section>
}

export default Schedule;
