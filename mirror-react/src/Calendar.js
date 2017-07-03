import React, { Component } from 'react';
import './css/Calendar.css';

class Calendar extends Component {

  render() {
    return (
      <section className="calendar">
        <div className="title">July</div>
        <div className="row">
          <div className="column-calendar">Sun</div>
          <div className="column-calendar">Mon</div>
          <div className="column-calendar">Tue</div>
          <div className="column-calendar">Wed</div>
          <div className="column-calendar">Thu</div>
          <div className="column-calendar">Fri</div>
          <div className="column-calendar">Sat</div>
        </div>
        <div className="row">
          <div className="column-calendar inactive">25</div>
          <div className="column-calendar inactive">26</div>
          <div className="column-calendar inactive">27</div>
          <div className="column-calendar inactive">28</div>
          <div className="column-calendar inactive">29</div>
          <div className="column-calendar inactive">30</div>
          <div className="column-calendar">1</div>
        </div>
        <div className="row">
          <div className="column-calendar">2</div>
          <div className="column-calendar today">3</div>
          <div className="column-calendar">4</div>
          <div className="column-calendar">5</div>
          <div className="column-calendar">6</div>
          <div className="column-calendar">7</div>
          <div className="column-calendar">8</div>
        </div>
        <div className="row">
          <div className="column-calendar">9</div>
          <div className="column-calendar">10</div>
          <div className="column-calendar">11</div>
          <div className="column-calendar">12</div>
          <div className="column-calendar">13</div>
          <div className="column-calendar">14</div>
          <div className="column-calendar">15</div>
        </div>
        <div className="row">
          <div className="column-calendar">16</div>
          <div className="column-calendar">17</div>
          <div className="column-calendar">18</div>
          <div className="column-calendar">19</div>
          <div className="column-calendar">20</div>
          <div className="column-calendar">21</div>
          <div className="column-calendar">22</div>
        </div>
        <div className="row">
          <div className="column-calendar">23</div>
          <div className="column-calendar">24</div>
          <div className="column-calendar">25</div>
          <div className="column-calendar">26</div>
          <div className="column-calendar">27</div>
          <div className="column-calendar">28</div>
          <div className="column-calendar">29</div>
        </div>
        <div className="row">
          <div className="column-calendar">30</div>
          <div className="column-calendar">31</div>
          <div className="column-calendar inactive">1</div>
          <div className="column-calendar inactive">2</div>
          <div className="column-calendar inactive">3</div>
          <div className="column-calendar inactive">4</div>
          <div className="column-calendar inactive">5</div>
        </div>
      </section>
    );
  }
}

export default Calendar;
