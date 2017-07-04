import React from 'react';
import { mount } from 'enzyme';
import Calendar from '../Calendar';

// Enable for jest debugging from IntelliJ
import jsdom from 'jsdom';
const doc = jsdom.jsdom('<!doctype html><html><body></body></html>');
global.document = doc;
global.window = doc.defaultView

const feb28_2016 = new Date(1456700203000); // leap year

const june1Calendar = mount(<Calendar date={ new Date(1496354203000) } />);
const july4Calendar = mount(<Calendar date={ new Date(1499183276000) }/>);

describe('<Calendar /> month', () => {
  it('shows june', () => {
    expect(june1Calendar.find(".title").text()).toBe("June");
  });

  it('shows july', () => {
    expect(july4Calendar.find(".title").text()).toBe("July");
  });
});

describe('<Calendar /> weeks', () => {
  it('has 5 lines for june', () => {
    expect(june1Calendar.find(".row")).toHaveLength(6);
  });

  it('has 6 lines for july', () => {
    expect(july4Calendar.find(".row")).toHaveLength(7);
  });

  it('lists inactive days for july', () => {
    const days = july4Calendar.find(".row");

    expect(days.at(0).children().map(day => day.text())).toEqual(["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]);
    expect(days.at(1).find('.inactive').map(day => day.text())).toEqual(["25", "26", "27", "28", "29", "30"]);
    expect(days.at(2).find('.inactive').map(day => day.text())).toHaveLength(0);
    expect(days.at(6).find('.inactive').map(day => day.text())).toEqual(["1", "2", "3", "4", "5"]);
  });
});

describe('<Calendar /> today', () => {
  it('is 1 for june', () => {
    expect(june1Calendar.find(".today").text()).toBe("1");
  });

  it('is 4 for july', () => {
    expect(july4Calendar.find(".today").text()).toBe("4");
  });
});

// TODO: shouldn't render unless the day changes.
