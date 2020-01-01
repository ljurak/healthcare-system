import React from 'react';
import ReactDOM from 'react-dom';
import { createBrowserHistory } from 'history';
import './index.css';

import { clearAlert } from './actions';
import Root from './components/Root';
import configureStore from './store/configureStore';

const store = configureStore();

const history = createBrowserHistory();
history.listen(location => store.dispatch(clearAlert()));

ReactDOM.render(
	<Root store={store} history={history} />, 
	document.getElementById('root')
);
