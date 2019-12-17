import React from 'react';
import { connect } from 'react-redux';

import PatientAddForm from './PatientAddForm';
import PatientSearchForm from './PatientSearchForm';
import PatientsList from './PatientsList';
import { getVisiblePatients } from '../reducers';
import { addPatient } from '../actions';

class PatientsPage extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			showSearchResults: false
		};
	}

	setShowSearchResults = (showResults) => {
		this.setState({ showSearchResults: showResults });
	}

	render() {
		const { patients, addPatient } = this.props;
		const { showSearchResults } = this.state;
		const path = this.props.match.path;
		return (
			<React.Fragment>
				<PatientAddForm addPatient={addPatient} />
				<PatientSearchForm setShowSearchResults={this.setShowSearchResults} />
				{ showSearchResults && <PatientsList patients={patients} /> }
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state) => ({
	patients: getVisiblePatients(state)
});

const mapDispatchToProps = { addPatient };

export default connect(
	mapStateToProps, 
	mapDispatchToProps
)(PatientsPage);