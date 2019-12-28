import React from 'react';
import { connect } from 'react-redux';

import PatientInfo from './PatientInfo';
import PatientVisitAddForm from './PatientVisitAddForm';
import VisitsList from '../VisitsList';
import { 
	fetchPatientById, 
	fetchDoctorsBySpecialty, 
	fetchSpecialties,
	fetchVisitsByPatientId,
	updatePatient, 
	addVisit, 
	clearAlert } from '../../actions';
import { 
	getPatient, 
	getVisibleSpecialties,
	getVisibleVisits,
	getIsUpdatingPatient,
	getIsFetchingVisits,
	getPatientsAlert } from '../../reducers';

class PatientDetailsPage extends React.Component {
	
	componentDidMount() {
		const { patientId } = this.props.match.params;
		this.props.fetchPatientById(patientId);
		this.props.fetchVisitsByPatientId(patientId);
		this.props.fetchSpecialties();
	}

	componentDidUpdate(prevProps) {
		if (this.props.patient !== prevProps.patient) {
			const { patientId } = this.props.match.params;
			this.props.fetchPatientById(patientId);
			this.props.fetchVisitsByPatientId(patientId);
		}
	}

	render() {
		const { 
			patient, 
			patientId, 
			specialties,
			visits,
			isFetchingVisits,
			isUpdatingPatient, 
			fetchDoctorsBySpecialty,
			updatePatient, 
			addVisit, 
			alert, 
			clearAlert } = this.props;

		return (
			<React.Fragment>
				<PatientInfo 
					patient={patient} 
					updatePatient={updatePatient}
					isUpdating={isUpdatingPatient}
					alert={alert}
					clearAlert={clearAlert} />
				<PatientVisitAddForm
					patientId={patientId}
					specialties={specialties} 
					fetchDoctors={fetchDoctorsBySpecialty}
					addVisit={addVisit} />
				<h3 className="visits-list-title">Patient's visits</h3>
				{ isFetchingVisits 
					? (<div className="visits-search-info">Loading visits...</div>)
					: (visits.length > 0)
						? (<VisitsList visits={visits} />)
						: (<div className="visits-search-info">No visits</div>) 
				}
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	const patientId = ownProps.match.params.patientId;
	return {
		patientId,
		patient: getPatient(state, patientId),
		isUpdatingPatient: getIsUpdatingPatient(state),
		specialties: getVisibleSpecialties(state),
		visits: getVisibleVisits(state),
		isFetchingVisits: getIsFetchingVisits(state),
		alert: getPatientsAlert(state)
	};
};

const mapDispatchToProps = { 
	fetchPatientById, 
	fetchDoctorsBySpecialty, 
	fetchSpecialties,
	fetchVisitsByPatientId,
	updatePatient,
	addVisit,
	clearAlert
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(PatientDetailsPage);