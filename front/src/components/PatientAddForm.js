import React from 'react';
import { connect } from 'react-redux';

import { addPatient } from '../actions';

class PatientAddForm extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			firstName: '',
			lastName: '',
			birthDate: '',
			address: '',
			phoneNumber: '',
			email: '',
			firstNameValid: true,
			lastNameValid: true,
			birthDateValid: true,
			addressValid: true,
			phoneNumberValid: true,
			emailValid: true
		};
	}

	handleSubmit = (e) => {
		e.preventDefault();
		if (!this.validateForm()) {
			return;
		}

		let { firstName, lastName, birthDate, address, phoneNumber, email } = this.state;
		if (!email) {
			email = null;
		}
		const patient = {
			firstName,
			lastName,
			birthDate,
			address,
			phoneNumber,
			email
		};

		this.props.addPatient(patient);
	}

	validateLength = (value, min, max) => {
		if (value.length < min || value.length > max) {
			return false;
		}
		return true;
	}

	validateFirstName = (e) => {
		const value = e.target.value;
		const isValid = this.validateLength(value, 3, 40);
		this.setState({ firstName: value, firstNameValid: isValid });
	}

	validateLastName = (e) => {
		const value = e.target.value;
		const isValid = this.validateLength(value, 3, 40);
		this.setState({ lastName: value, lastNameValid: isValid });
	}

	validateBirthDate = (e) => {
		const value = e.target.value;
		const birthDateRegex = /^(19[0-9]{2}|20[0-2][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
		let isValid = true;
		if (!birthDateRegex.test(value)) {
			isValid = false;
		}
		this.setState({ birthDate: value, birthDateValid: isValid });
	}

	validateAddress = (e) => {
		const value = e.target.value;
		const isValid = this.validateLength(value, 10, 100);
		this.setState({ address: value, addressValid: isValid });
	}

	validatePhoneNumber = (e) => {
		const value = e.target.value;
		const phoneRegex = /^\+?[0-9]{9,15}$/;
		let isValid = true;
		if (!phoneRegex.test(value)) {
			isValid = false;
		}
		this.setState({ phoneNumber: value, phoneNumberValid: isValid });
	}

	validateEmail = (e) => {
		const value = e.target.value;
		const emailRegex = /^[0-9a-zA-Z_.-]+@[0-9a-zA-Z.-]+\.[a-zA-Z]{2,3}$/i;
		let isValid = true;
		if (value.length && !emailRegex.test(value)) {
			isValid = false;
		}
		this.setState({ email: value, emailValid: isValid });
	}

	validateForm = () => {
		return this.state.firstName && this.state.firstNameValid && this.state.lastName && this.state.lastNameValid
			&& this.state.birthDate && this.state.birthDateValid && this.state.address && this.state.addressValid
			&& this.state.phoneNumber && this.state.phoneNumberValid && this.state.emailValid;
	}

	render() {
		const { firstName, lastName, birthDate, address, phoneNumber, email } = this.state;

		return (
			<form className="form" onSubmit={this.handleSubmit}>			
				<div className={'form-row' + (this.state.firstNameValid ? '' : ' error')}>
					<label htmlFor="firstName">First name*</label>
					<input id="firstName" name="firstName" type="text" value={firstName} onChange={this.validateFirstName} />
				</div>
				<div className={'form-row' + (this.state.lastNameValid ? '' : ' error')}>
					<label htmlFor="lastName">Last name*</label>
					<input id="lastName" name="lastName" type="text" value={lastName} onChange={this.validateLastName} />
				</div>
				<div className={'form-row' + (this.state.birthDateValid ? '' : ' error')}>
					<label htmlFor="birthDate">Birth date*</label>
					<input id="birthDate" name="birthDate" type="text" placeholder="YYYY-MM-DD" value={birthDate} onChange={this.validateBirthDate} />
				</div>
				<div className={'form-row' + (this.state.addressValid ? '' : ' error')}>
					<label htmlFor="address">Address*</label>
					<input id="address" name="address" type="text" value={address} onChange={this.validateAddress} />
				</div>
				<div className={'form-row' + (this.state.phoneNumberValid ? '' : ' error')}>
					<label htmlFor="phoneNumber">Phone number*</label>
					<input id="phoneNumber" name="phoneNumber" type="text" value={phoneNumber} onChange={this.validatePhoneNumber} />
				</div>
				<div className={'form-row' + (this.state.emailValid ? '' : ' error')}>
					<label htmlFor="email">Email</label>
					<input id="email" name="email" type="email" value={email} onChange={this.validateEmail} />
				</div>
				<div className="form-row btn">
					<button className="submit-btn">
						Add patient
					</button>
				</div>		
			</form>
		);
	}
}

const mapDispatchToProps = { addPatient };

export default connect(
	null, 
	mapDispatchToProps
)(PatientAddForm);