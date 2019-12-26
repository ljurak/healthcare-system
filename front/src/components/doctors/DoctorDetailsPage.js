import React from 'react';
import { connect } from 'react-redux';

import DoctorInfo from './DoctorInfo';
import { fetchDoctorById, updateDoctor } from '../../actions';
import { getDoctor, getIsUpdatingDoctor } from '../../reducers';

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
		return (
			<React.Fragment>
				<DoctorInfo 
					doctor={this.props.doctor} 
					updateDoctor={this.props.updateDoctor}
					isUpdating={this.props.isUpdating} />
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	const doctorId = ownProps.match.params.doctorId;
	return {
		doctorId,
		doctor: getDoctor(state, doctorId),
		isUpdating: getIsUpdatingDoctor(state)
	};
};

const mapDispatchToProps = { 
	fetchDoctorById,   
	updateDoctor
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(DoctorDetailsPage);