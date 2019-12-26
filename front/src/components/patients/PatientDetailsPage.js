import React from 'react';
import { connect } from 'react-redux';

import PatientInfo from './PatientInfo';
import PatientVisitAddForm from './PatientVisitAddForm';
import { 
	fetchPatientById, 
	fetchDoctorsBySpecialty, 
	fetchSpecialties, 
	updatePatient, 
	addVisit, 
	clearAlert } from '../../actions';
import { 
	getPatient, 
	getVisibleSpecialties, 
	getIsUpdatingPatient, 
	getPatientsAlert } from '../../reducers';

class PatientDetailsPage extends React.Component {
	
	componentDidMount() {
		const { patientId } = this.props.match.params;
		this.props.fetchPatientById(patientId);
		this.props.fetchSpecialties();
	}

	componentDidUpdate(prevProps) {
		if (this.props.patient !== prevProps.patient) {
			const { patientId } = this.props.match.params;
			this.props.fetchPatientById(patientId);
		}
	}

	render() {
		const { patient, patientId, specialties, isUpdating, fetchDoctors, updatePatient, addVisit, alert, clearAlert } = this.props;

		return (
			<React.Fragment>
				<PatientInfo 
					patient={patient} 
					updatePatient={updatePatient}
					isUpdating={isUpdating}
					alert={alert}
					clearAlert={clearAlert} />
				<PatientVisitAddForm
					patientId={patientId}
					specialties={specialties} 
					fetchDoctors={fetchDoctorsBySpecialty}
					addVisit={addVisit} />
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	const patientId = ownProps.match.params.patientId;
	return {
		patientId,
		patient: getPatient(state, patientId),
		isUpdating: getIsUpdatingPatient(state),
		specialties: getVisibleSpecialties(state),
		alert: getPatientsAlert(state)
	};
};

const mapDispatchToProps = { 
	fetchPatientById, 
	fetchDoctorsBySpecialty, 
	fetchSpecialties, 
	updatePatient,
	addVisit,
	clearAlert
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(PatientDetailsPage);