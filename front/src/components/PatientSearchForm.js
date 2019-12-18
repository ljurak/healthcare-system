import React from 'react';

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
		this.props.fetchPatients(searchValue);
		this.setState({	searchValue: ''	});
	}

	render() {
		const searchValue = this.state.searchValue;
		const { isFetching } = this.props;

		return (
			<React.Fragment>
				<h3 className="patients-search-title">Search patients</h3>
				<form className="form" onSubmit={this.handleSubmit}>
					<div className="form-row">
						<label htmlFor="lastname">Patient's last name</label>
						<input id="lastname" type="text" value={searchValue} onChange={this.handleChange} />
					</div>
					<div className="form-row btn">
						<button className={'submit-btn' + (isFetching ? ' loading' : '')}>
							Search
						</button>
					</div>
				</form>
			</React.Fragment>
		);
	}
}

export default PatientSearchForm;