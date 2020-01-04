import React from 'react';
import { connect } from 'react-redux';

import DoctorInfo from './DoctorInfo';
import DoctorVisitSearchForm from './DoctorVisitSearchForm';
import { 
	fetchDoctorById, 
	fetchVisitsByDoctorId,
	updateDoctor, 
	clearAlert } from '../../actions';
import { 
	getDoctor, 
	getIsUpdatingDoctor, 
	getIsFetchingVisits,
	getDoctorsAlert } from '../../reducers';

class DoctorDetailsPage extends React.Component {
	
	componentDidMount() {
		const { doctorId } = this.props.match.params;
		this.props.fetchDoctorById(doctorId);
	}

	componentDidUpdate(prevProps) {
		if (this.props.match.params.doctorId !== prevProps.match.params.doctorId) {
			const { doctorId } = this.props.match.params;
			this.props.fetchDoctorById(doctorId);
		}
	}

	render() {
		const { 
			doctor,
			doctorId, 
			isUpdatingDoctor, 
			updateDoctor,
			fetchVisitsByDoctorId, 
			isFetchingVisits,
			alert, 
			clearAlert, } = this.props;

		return (
			<React.Fragment>
				<DoctorInfo 
					doctor={doctor} 
					updateDoctor={updateDoctor}
					isUpdating={isUpdatingDoctor}
					alert={alert}
					clearAlert={clearAlert} />
				<DoctorVisitSearchForm
					doctorId={doctorId}
					fetchVisits={fetchVisitsByDoctorId}
					isFetching={isFetchingVisits} />
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	const doctorId = ownProps.match.params.doctorId;
	return {
		doctorId,
		doctor: getDoctor(state, doctorId),
		isUpdatingDoctor: getIsUpdatingDoctor(state),
		isFetchingVisits: getIsFetchingVisits(state),
		alert: getDoctorsAlert(state)
	};
};

const mapDispatchToProps = { 
	fetchDoctorById,
	fetchVisitsByDoctorId, 
	updateDoctor,
	clearAlert
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(DoctorDetailsPage);