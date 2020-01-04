import React from 'react';
import { connect } from 'react-redux';

import { getVisibleDoctors } from '../../reducers';

const PatientVisitAddFormDoctorsField = ({ id, field, doctors }) => (
	<select id={id} {...field}>
		<option value="">-- Choose a doctor --</option>
		{doctors.map(doctor => (
			<option key={doctor.id} value={doctor.id}>{doctor.firstName + ' ' + doctor.lastName}</option>
		))}
	</select>
);

const mapStateToProps = (state) => {
	return {
		doctors: getVisibleDoctors(state)
	};
};

export default connect(
	mapStateToProps
)(PatientVisitAddFormDoctorsField);