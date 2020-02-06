import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { createLogger } from 'redux-logger';

import rootReducer from '../reducers';

export default function configureStore(preloadedState) {
	const middlewares = [ thunkMiddleware ];

	if (process.env.NODE_ENV !== 'PRODUCTION') {
		middlewares.push(createLogger());
	}

	return createStore(rootReducer, preloadedState, applyMiddleware(...middlewares));
};
