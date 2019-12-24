import React from 'react';
import { Switch, Route } from 'react-router-dom';

//import DoctorDetailsPage from './DoctorDetailsPage';
import DoctorsPage from './DoctorsPage';

const DoctorComponent = ({ match: { path } }) => (
	<Switch>
		{/*<Route path={`${path}/:doctorId`} component={DoctorDetailsPage} />*/}
		<Route path={path} component={DoctorsPage} />
	</Switch>
);


export default DoctorComponent;