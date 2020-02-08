import React from 'react';
import { Redirect, Route } from 'react-router-dom';

import { getToken } from '../helpers/token';

const PrivateRoute = ({ component: Component, ...rest }) => (
	<Route {...rest} render={props =>
		getToken() 
			? <Component {...props} />
			: <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
	} />
);

export default PrivateRoute;