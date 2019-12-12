import React from 'react';
import { connect } from 'react-redux';

import PatientAddForm from './PatientAddForm';
import PatientSearchForm from './PatientSearchForm';
import PatientsList from './PatientsList';
import { getVisiblePatients } from '../reducers';

class PatientsPage extends React.Component {
	render() {
		const { patients } = this.props;
		return (
			<React.Fragment>
				<PatientAddForm />
				<PatientSearchForm />
				<PatientsList patients={patients} />
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state) => ({
	patients: getVisiblePatients(state)
});

export default connect(mapStateToProps)(PatientsPage);