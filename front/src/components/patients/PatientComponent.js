import React from 'react';
import { Switch, Route } from 'react-router-dom';

import PatientDetailsPage from './PatientDetailsPage';
import PatientsPage from './PatientsPage';

const PatientComponent = ({ match: { path } }) => (
	<Switch>
		<Route path={`${path}/:patientId`} component={PatientDetailsPage} />
		<Route path={path} component={PatientsPage} />
	</Switch>
);


export default PatientComponent;