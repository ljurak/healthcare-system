import React from 'react';
import { connect } from 'react-redux';

import PatientAddForm from './PatientAddForm';
import PatientSearchForm from './PatientSearchForm';
import PatientsList from '../PatientsList';
import { getVisiblePatients, getIsFetchingPatients, getIsAddingPatient } from '../../reducers';
import { addPatient, fetchPatientsByLastname } from '../../actions';

const PatientsPage = ({ patients, isFetching, isAdding, fetchPatientsByLastname, addPatient }) => (
	<React.Fragment>
		<PatientAddForm isAdding={isAdding} addPatient={addPatient} />
		<PatientSearchForm isFetching={isFetching} fetchPatients={fetchPatientsByLastname} />
		{ patients.length > 0
			? (<PatientsList patients={patients} />)
			: (<div className="patients-search-info">No results</div>) 
		}
	</React.Fragment>
);

const mapStateToProps = (state) => ({
	patients: getVisiblePatients(state),
	isFetching: getIsFetchingPatients(state),
	isAdding: getIsAddingPatient(state)
});

const mapDispatchToProps = { 
	addPatient, 
	fetchPatientsByLastname 
};

export default connect(
	mapStateToProps, 
	mapDispatchToProps
)(PatientsPage);