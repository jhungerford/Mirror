import React from 'react';
import ReactDOM from 'react-dom';
import Mirror from './Mirror';

import './css/index.css';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<Mirror />, document.getElementById('root'));
registerServiceWorker();
