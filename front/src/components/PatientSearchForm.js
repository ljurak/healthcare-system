import React from 'react';
import { connect } from 'react-redux';
import { fetchPatientsByLastname } from '../actions';

class PatientSearchForm extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			searchValue: ''
		};
	}

	handleChange = (e) => {
		this.setState({
			searchValue: e.target.value
		});
	}

	handleSubmit = (e) => {
		e.preventDefault();

		const searchValue = this.state.searchValue;
		this.props.fetchPatientsByLastname(searchValue);
		this.props.setShowSearchResults(true);

		this.setState({	searchValue: ''	});
	}

	render() {
		const searchValue = this.state.searchValue;

		return (
			<form className="form" onSubmit={this.handleSubmit}>
				<div className="form-row">
					<label htmlFor="lastname">Patient's last name</label>
					<input id="lastname" type="text" value={searchValue} onChange={this.handleChange} />
				</div>
				<div className="form-row btn">
					<button className="submit-btn">
						Search
					</button>
				</div>
			</form>
		);
	}
}

const mapDispatchToProps = { fetchPatientsByLastname };

export default connect(
	null,
	mapDispatchToProps
)(PatientSearchForm);