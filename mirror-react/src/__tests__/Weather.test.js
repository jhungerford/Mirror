import React from "react";
import {mount} from "enzyme";
import Weather from "../Weather";

// Enable for jest debugging from IntelliJ
// import jsdom from 'jsdom';
// const doc = jsdom.jsdom('<!doctype html><html><body></body></html>');
// global.document = doc;
// global.window = doc.defaultView

const width = (column) => {
  return parseInt(column.getDOMNode().style.width)
};

const sum = (acc, value) => isNaN(value) ? acc : acc + value;

describe('<Weather data={ mockupWeather } />', () => {
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

  const weather = mount(<Weather data={ mockupWeather } />);

  it('shows the current weather', () => {
    expect(weather.find('.title').text()).toContain('80° Clear')
  });

  it('shows the forecast', () => {
    const forecastRows = weather.find('.row-weather');
    expect(forecastRows).toHaveLength(3);

    const todayColumns = forecastRows.at(0).children();
    expect(todayColumns).toHaveLength(5);

    expect(forecastRows.map(row => row.find('.column-weather-day').text())).toEqual(['Today', 'Tue', 'Wed']);
    expect(forecastRows.map(row => row.find('.column-weather-range-min').text())).toEqual(['61°', '66°', '65°']);
    expect(forecastRows.map(row => row.find('.column-weather-range-max').text())).toEqual(['90°', '89°', '96°']);
    expect(forecastRows.map(row => width(row.find('.column-weather-range-bar')))).toEqual([39, 31, 42]);

    // Each weather bar should be 75% of the width of the row, and the numbers should be 14% at the lowest
    forecastRows.forEach(row => {
      expect(width(row.find('.column-weather-range-min'))).toBeGreaterThanOrEqual(14);
      expect(width(row.find('.column-weather-range-max'))).toBeGreaterThanOrEqual(14);

      const sumWidths = row.find('.column')
        .map(column => width(column))
        .reduce(sum, 0);

      expect(sumWidths).toBe(75);
    })
  })
});

describe('<Weather data={ laWeather } />', () => {
  const laWeather = {
    current: {
      icon: 'partly-cloudy-day-day',
      temperature: 63.38,
      summary: 'Partly Cloudy'
    },
    forecast: [
      {time: 1499151600, day: 'Today', icon: 'clear-day', low: 51.6, high: 64.7},
      {time: 1499238000, day: 'Wed', icon: 'partly-cloudy-day', low: 52.65, high: 62.93},
      {time: 1499324400, day: 'Thu', icon: 'partly-cloudy-day', low: 51.05, high: 66.56}
    ]
  };

  const weather = mount(<Weather data={ laWeather } />);

  it('shows the current weather', () => {
    expect(weather.find('.title').text()).toContain('63° Partly Cloudy')
  });

  it('shows the forecast', () => {
    const forecastRows = weather.find('.row-weather');
    expect(forecastRows).toHaveLength(3);

    const todayColumns = forecastRows.at(0).children();
    expect(todayColumns).toHaveLength(5);

    expect(forecastRows.map(row => row.find('.column-weather-day').text())).toEqual(['Today', 'Wed', 'Thu']);
    expect(forecastRows.map(row => row.find('.column-weather-range-min').text())).toEqual(['52°', '53°', '51°']);
    expect(forecastRows.map(row => row.find('.column-weather-range-max').text())).toEqual(['65°', '63°', '67°']);
    expect(forecastRows.map(row => width(row.find('.column-weather-range-bar')))).toEqual([40, 31, 47]);

    // Each weather bar should be 75% of the width of the row, and the numbers should be 14% at the lowest
    forecastRows.forEach(row => {
      expect(width(row.find('.column-weather-range-min'))).toBeGreaterThanOrEqual(14);
      expect(width(row.find('.column-weather-range-max'))).toBeGreaterThanOrEqual(14);

      const sumWidths = row.find('.column')
        .map(column => width(column))
        .reduce(sum, 0);

      expect(sumWidths).toBe(75);
    })
  })
});
