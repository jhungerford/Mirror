import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {IntlProvider} from 'react-intl';
import './css/index.css';

ReactDOM.render(
    <IntlProvider locale="en">
      <App />
    </IntlProvider>,
    document.getElementById('root')
);
