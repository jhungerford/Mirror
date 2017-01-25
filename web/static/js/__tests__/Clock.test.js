import React from 'react';
import { shallow, mount, unmount } from 'enzyme';

jest.useFakeTimers();

import Clock from '../Clock';

const realNow = Clock.now;
afterEach(() => {
  Clock.now = realNow;
});

it('pads numbers < 10', () => {
  expect(Clock.padNumber(0)).toBe('00');
  expect(Clock.padNumber(9)).toBe('09');
});

it('does not pad numbers > 10', () => {
  expect(Clock.padNumber(10)).toBe('10');
  expect(Clock.padNumber(59)).toBe('59');
});

it('displays the current time', () => {
  Clock.now = jest.fn()
      .mockImplementationOnce(() => new Date(2017, 0, 19, 7, 55, 3));

  const clock = shallow(<Clock />);

  expect(clock.find('.clock-time').text()).toBe('7:55:03');
  expect(clock.find('.clock-date').text()).toBe('Thursday, January 19, 2017');
});

it('de-registers the timer when removed', () => {
  jest.resetAllMocks();

  expect(setInterval.mock.calls.length).toBe(0);

  const clock = mount(<Clock />);

  expect(setInterval.mock.calls.length).toBe(1);

  clock.unmount();

  expect(clearInterval.mock.calls.length).toBe(1);
});

it('changes when the second changes', () => {
  jest.resetAllMocks();

  Clock.now = jest.fn()
      .mockImplementationOnce(() => new Date(2017, 0, 19, 7, 55, 3))
      .mockImplementationOnce(() => new Date(2017, 0, 19, 7, 55, 4));

  const clock = mount(<Clock />);

  expect(setInterval.mock.calls.length).toBe(1);

  expect(clock.find('.clock-time').text()).toBe('7:55:03');
  expect(clock.find('.clock-date').text()).toBe('Thursday, January 19, 2017');

  // TODO: jest.runAllTimers() or jest.runTimersToTime(1001) _should_ call Clock.tick.
  setInterval.mock.calls[0][0].call();

  expect(clock.find('.clock-time').text()).toBe('7:55:04');
  expect(clock.find('.clock-date').text()).toBe('Thursday, January 19, 2017');
});
