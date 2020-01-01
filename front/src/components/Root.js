import React from 'react';
import { Router } from 'react-router-dom';
import { Provider } from 'react-redux';

import App from './App';

const Root = ({ store, history }) => (
	<Provider store={store}>
		<Router history={history}>
			<App />
		</Router>
	</Provider>
);

export default Root;