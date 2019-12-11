import React from 'react';
import { connect } from 'react-redux';

import PatientSearchForm from './PatientSearchForm';
import PatientsList from './PatientsList';
import { getVisiblePatients } from '../reducers';

class PatientsPage extends React.Component {
	render() {
		const { patients } = this.props;
		return (
			<React.Fragment>
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