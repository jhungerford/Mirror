import React from 'react';
import { mount } from 'enzyme';
import Schedule from '../Schedule';

// Enable for jest debugging from IntelliJ
// import jsdom from 'jsdom';
// const doc = jsdom.jsdom('<!doctype html><html><body></body></html>');
// global.document = doc;
// global.window = doc.defaultView;

describe('<Schedule data={ mockupData } />', () => {
  const mockupData = [
    {
      date: '2017-07-03',
      events: []
    }, {
      date: '2017-07-04',
      events: [
        {
          id: '1',
          description: 'Independence Day'
        },{
          id: '2',
          time: '2017-07-04T14:30:00.000Z',
          description: 'Coffee'
        },{
          id: '3',
          time: '2017-07-04T16:30:00.000Z',
          description: 'Engineering Leads Daily'
        },{
          id: '4',
          time: '2017-07-04T17:00:00.000Z',
          description: 'Standup'
        },{
          id: '5',
          time: '2017-07-04T19:00:00.000Z',
          description: 'Event with a name that\'s too long to fit in a row comfortably.'
        }
      ]
    }
  ];

  const schedule = mount(<Schedule schedule={ mockupData } />);

  it('Shows both days', () => {
    const days = schedule.find('.title');

    expect(days).toHaveLength(2);
    expect(days.at(0).text()).toBe("Monday, July 3");
    expect(days.at(1).text()).toBe("Tuesday, July 4");
  });

  it('Lists no events for July 3', () => {
    const days = schedule.find('.schedule-day');
    const events = days.at(0).find('.column');

    expect(events).toHaveLength(1);
    expect(events.at(0).hasClass('column-schedule-all')).toBe(true);
    expect(events.at(0).text()).toBe('No More Events');
  });

  it('Lists events for July 4', () => {
    const days = schedule.find('.schedule-day');
    const events = days.at(1).find('.row');

    expect(events).toHaveLength(5);
    expect(events.at(0).find('.column').hasClass('column-schedule-all')).toBe(true);
    expect(events.at(0).find('.column').text()).toBe('Independence Day');

    expect(events.at(1).find('.column').at(0).hasClass('column-schedule-time')).toBe(true);
    expect(events.at(1).find('.column').at(0).text()).toBe('8:30');
    expect(events.at(1).find('.column').at(1).hasClass('column-schedule-event')).toBe(true);
    expect(events.at(1).find('.column').at(1).text()).toBe('Coffee');
  });
});