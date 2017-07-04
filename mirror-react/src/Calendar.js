import React, { Component } from 'react';
import './css/Calendar.css';

const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
const dayNames = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"].map(day => ({
  value: day,
  inactive: false,
  today: false
}));

function Month(props) {
  return <div className="title">{ months[props.date.getMonth()] }</div>
}

function Week(props) {
  return <div className="row">
    { props.days.map(day =>
      <Day key={ day.value + (day.inactive ? '-inactive' : '' )}
           value={ day.value }
           inactive={ day.inactive }
           today={ day.today } />
    )}
  </div>;
}

function Day(props) {
  const className = "column column-calendar" +
    (props.inactive ? ' inactive' : '') +
    (props.today ? ' today' : '');

  return <div className={ className }>{ props.value }</div>
}

class Calendar extends Component {
  render() {
    const now = this.props.date;
    const monthStart = new Date(now.getFullYear(), now.getMonth(), 1);
    const monthEnd = new Date(now.getFullYear(), now.getMonth() + 1, 0);

    const startDay = monthStart.getDay();
    const daysInMonth = (monthEnd - monthStart) / (24 * 60 * 60 * 1000) + 1;
    const daysInLastMonth = new Date(now.getFullYear(), now.getMonth(), 0).getDate();

    const numWeeks = Math.ceil((startDay + daysInMonth) / 7);

    const range = (start, end) => Array.from({length: (end - start)}, (value, key) => key);

    // Flat array of days that the calendar will display.
    let calendarDays = [];

    for (let prevMonthDay = daysInLastMonth - startDay + 1; prevMonthDay <= daysInLastMonth; prevMonthDay ++) {
      calendarDays[calendarDays.length] = {
        value: prevMonthDay,
        inactive: true,
        today: false
      };
    }

    for (let monthIndex = 1; monthIndex <= daysInMonth; monthIndex ++) {
      calendarDays[calendarDays.length] = {
        value: monthIndex,
        inactive: false,
        today: (monthIndex === now.getDate())
      }
    }

    const remainingDays = 7 - (startDay + daysInMonth) % 7;
    if (remainingDays < 7) {
      for (let nextMonthDay = 1; nextMonthDay <= remainingDays; nextMonthDay ++) {
        calendarDays[calendarDays.length] = {
          value: nextMonthDay,
          inactive: true,
          today: false
        };
      }
    }

    return (
      <section className="calendar">
        <Month date={ this.props.date } />
        <Week days={ dayNames } />

        { range(0, numWeeks).map(weekNum =>
          <Week key={now.getMonth() + '-' + weekNum} days={ calendarDays.slice(weekNum * 7, weekNum * 7 + 7) } />
        )}
      </section>
    );
  }
}

export default Calendar;
