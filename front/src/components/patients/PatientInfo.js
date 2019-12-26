import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

class PatientInfo extends React.Component {

	handleSubmit = (values, actions) => {
		const patient = { ...this.props.patient };

		patient.address = values.address;
		patient.phoneNumber = values.phoneNumber;
		patient.email = values.email;
		if (!patient.email) {
			patient.email = null;
		}

		this.props.updatePatient(patient, patient.id)
			.finally(() => {
				actions.setSubmitting(false);
			});
	}

	render() {
		const { patient, isUpdating } = this.props;
		if (!patient) {
			return <div>Loading...</div>;
		}

		let { firstName, lastName, birthDate, address, phoneNumber, email } = patient;
		if (!email) {
			email = '';
		}

		return (
			<React.Fragment>
				<h3 className="patient-info-title">Patient info</h3>
				<Formik
					enableReinitialize={true}
					initialValues={{ firstName, lastName, birthDate, address, phoneNumber, email }}
					validationSchema={Yup.object({
						address: Yup.string()
							.min(10, 'Must be 10 characters or more')
							.max(100, 'Must be 100 characters or less')
							.required('Required'),
						phoneNumber: Yup.string()
							.matches(/^\+?[0-9]{9,15}$/, 'Must be valid phone number (only digits)')
							.required('Required'),
						email: Yup.string()
							.email('Must be valid email')
					})}
					onSubmit={this.handleSubmit}>
					
					{({ errors, touched, isSubmitting }) => (
						<Form className="update-form">
							<div className="form-row">
								<label htmlFor="firstName">First name</label>
								<div className="form-field">
									<Field id="firstName" name="firstName" type="text" disabled={true} />
								</div>
							</div>
							<div className="form-row">
								<label htmlFor="lastName">Last name</label>
								<div className="form-field">
									<Field id="lastName" name="lastName" type="text" disabled={true} />
								</div>
							</div>
							<div className="form-row">
								<label htmlFor="birthDate">Birth date</label>
								<div className="form-field">
									<Field id="birthDate" name="birthDate" type="text" disabled={true} />
								</div>
							</div>
							<div className={'form-row' + (errors.address && touched.address ? ' error' : '')}>
								<label htmlFor="address">Address*</label>
								<div className="form-field">
									<Field id="address" name="address" type="text" />
									<ErrorMessage name="address" component="div" className="error" />
								</div>
							</div>
							<div className={'form-row' + (errors.phoneNumber && touched.phoneNumber ? ' error' : '')}>
								<label htmlFor="phoneNumber">Phone number*</label>
								<div className="form-field">
									<Field id="phoneNumber" name="phoneNumber" type="text" />
									<ErrorMessage name="phoneNumber" component="div" className="error" />
								</div>
							</div>
							<div className={'form-row' + (errors.email && touched.email ? ' error' : '')}>
								<label htmlFor="email">Email</label>
								<div className="form-field">
									<Field id="email" name="email" type="email" />
									<ErrorMessage name="email" component="div" className="error" />
								</div>
							</div>
							<div className="form-row btn">
								<button className={'submit-btn' + (isUpdating ? ' loading' : '')} type="submit" disabled={isSubmitting}>
									Update patient							
								</button>
							</div>
						</Form>
					)}					
				</Formik>
			</React.Fragment>
		);
	}
}

export default PatientInfo;