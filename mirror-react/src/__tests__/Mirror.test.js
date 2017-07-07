import React from 'react';
import ReactDOM from 'react-dom';
import Mirror from '../Mirror';

it('renders without crashing', () => {
  const mockupWeather = {
    current: {
      icon: 'clear-day',
      temperature: 80,
      summary: 'Clear'
    },
    forecast: [
      {time: 1499151600, day: 'Today', icon: 'rain', low: 61, high: 90},
      {time: 1499238000, day: 'Tue', icon: 'partly-cloudy-day', low: 66, high: 89},
      {time: 1499324400, day: 'Wed', icon: 'partly-cloudy-day', low: 65, high: 96}
    ]
  };

  const div = document.createElement('div');
  ReactDOM.render(<Mirror weather={ mockupWeather } />, div);
});
