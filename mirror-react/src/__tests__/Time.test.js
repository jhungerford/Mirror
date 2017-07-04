import React from 'react';
import ReactDOM from 'react-dom';
import Time from '../Time';

describe('padNumber', () => {
  it('pads a single digit with a 0', () => {
    expect(Time.padNumber(0)).toBe('00');
  });

  it('does not pad a two digit number', () => {
    expect(Time.padNumber(10)).toBe('10');
  });
});

describe('<Time />', () => {
  it('displays the time', () => {
    const div = document.createElement('div');
    ReactDOM.render(<Time date={ new Date(1499179007000) } />, div); // 8:36 MDT

    expect(div.textContent).toBe("8:36");
  });

  it('displays 24 hour time', () => {
    const div = document.createElement('div');
    ReactDOM.render(<Time date={ new Date(1499198461000) } />, div); // 14:01 MDT

    expect(div.textContent).toBe("14:01");
  });
});
