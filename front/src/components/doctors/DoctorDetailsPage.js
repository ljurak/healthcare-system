import React from 'react';
import { connect } from 'react-redux';

import DoctorInfo from './DoctorInfo';
import { 
	fetchDoctorById, 
	updateDoctor, 
	clearAlert } from '../../actions';
import { 
	getDoctor, 
	getIsUpdatingDoctor, 
	getDoctorsAlert } from '../../reducers';

class DoctorDetailsPage extends React.Component {
	
	componentDidMount() {
		const { doctorId } = this.props.match.params;
		this.props.fetchDoctorById(doctorId);
	}

	componentDidUpdate(prevProps) {
		if (this.props.doctor !== prevProps.doctor) {
			const { doctorId } = this.props.match.params;
			this.props.fetchDoctorById(doctorId);
		}
	}

	render() {
		const { doctor, isUpdating, updateDoctor, alert, clearAlert } = this.props;

		return (
			<React.Fragment>
				<DoctorInfo 
					doctor={doctor} 
					updateDoctor={updateDoctor}
					isUpdating={isUpdating}
					alert={alert}
					clearAlert={clearAlert} />
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	const doctorId = ownProps.match.params.doctorId;
	return {
		doctorId,
		doctor: getDoctor(state, doctorId),
		isUpdating: getIsUpdatingDoctor(state),
		alert: getDoctorsAlert(state)
	};
};

const mapDispatchToProps = { 
	fetchDoctorById,   
	updateDoctor,
	clearAlert
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(DoctorDetailsPage);