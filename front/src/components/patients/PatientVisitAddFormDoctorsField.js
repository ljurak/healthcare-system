import React from 'react';
import { connect } from 'react-redux';

import { getVisibleDoctorsBySpecialty } from '../../reducers';

const PatientVisitAddFormDoctorsField = ({ id, doctors, field }) => (
	<select id={id} {...field}>
		<option value="">-- Choose a doctor --</option>
		{doctors.map(doctor => (
			<option key={doctor.id} value={doctor.id}>{doctor.firstName + ' ' + doctor.lastName}</option>
		))}
	</select>
);

const mapStateToProps = (state) => {
	return {
		doctors: getVisibleDoctorsBySpecialty(state)
	};
};

export default connect(
	mapStateToProps
)(PatientVisitAddFormDoctorsField);