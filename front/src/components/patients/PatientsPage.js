import React from 'react';
import { connect } from 'react-redux';

import PatientAddForm from './PatientAddForm';
import PatientSearchForm from './PatientSearchForm';
import PeopleList from '../PeopleList';
import { 
	getVisiblePatients, 
	getIsFetchingPatients, 
	getIsAddingPatient, 
	getPatientsAlert } from '../../reducers';
import { 
	addPatient, 
	fetchPatientsByLastname, 
	clearAlert } from '../../actions';

const PatientsPage = ({ patients, isFetching, isAdding, fetchPatientsByLastname, addPatient, alert, clearAlert }) => (
	<React.Fragment>
		<PatientAddForm 
			isAdding={isAdding} 
			addPatient={addPatient} 
			alert={alert} 
			clearAlert={clearAlert} 
		/>
		<PatientSearchForm 
			isFetching={isFetching} 
			fetchPatients={fetchPatientsByLastname} 
		/>
		{ patients.length > 0
			? (<PeopleList people={patients} baseUrl="/patients" />)
			: (<div className="patients-search-info">No results</div>) 
		}
	</React.Fragment>
);

const mapStateToProps = (state) => ({
	patients: getVisiblePatients(state),
	isFetching: getIsFetchingPatients(state),
	isAdding: getIsAddingPatient(state),
	alert: getPatientsAlert(state)
});

const mapDispatchToProps = { 
	addPatient, 
	fetchPatientsByLastname,
	clearAlert 
};

export default connect(
	mapStateToProps, 
	mapDispatchToProps
)(PatientsPage);