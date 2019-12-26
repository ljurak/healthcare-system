import React from 'react';
import { connect } from 'react-redux';

import PatientInfo from './PatientInfo';
import PatientVisitAddForm from './PatientVisitAddForm';
import { fetchPatientById, fetchDoctorsBySpecialty, fetchSpecialties, updatePatient, addVisit } from '../../actions';
import { getPatient, getVisibleSpecialties, getIsUpdatingPatient } from '../../reducers';

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
		return (
			<React.Fragment>
				<PatientInfo 
					patient={this.props.patient} 
					updatePatient={this.props.updatePatient}
					isUpdating={this.props.isUpdating} />
				<PatientVisitAddForm
					patientId={this.props.patientId}
					specialties={this.props.specialties} 
					fetchDoctors={this.props.fetchDoctorsBySpecialty}
					addVisit={this.props.addVisit} />
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
		specialties: getVisibleSpecialties(state)
	};
};

const mapDispatchToProps = { 
	fetchPatientById, 
	fetchDoctorsBySpecialty, 
	fetchSpecialties, 
	updatePatient,
	addVisit
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(PatientDetailsPage);