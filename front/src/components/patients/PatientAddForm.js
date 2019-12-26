import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

class PatientAddForm extends React.Component {

	handleSubmit = (values, actions) => {
		const patient = { ...values };
		if (!patient.email) {
			patient.email = null;
		}

		this.props.addPatient(patient)
			.finally(() => {
				actions.setSubmitting(false);
				actions.resetForm();
			});
	}

	render() {
		const { isAdding, alert, clearAlert } = this.props;
		return (
			<React.Fragment>
				<h3 className="patient-add-form-title">Add new patient</h3>
				<Formik
					initialValues={{ firstName: '', lastName: '', birthDate: '', address: '', phoneNumber: '', email: ''}}
					validationSchema={Yup.object({
						firstName: Yup.string()
							.min(3, 'Must be 3 characters or more')
							.max(40, 'Must be 40 characters or less')
							.required('Required'),
						lastName: Yup.string()
							.min(3, 'Must be 3 characters or more')
							.max(40, 'Must be 40 characters or less')
							.required('Required'),
						birthDate: Yup.string()
							.matches(/^(19[0-9]{2}|20[0-2][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/, 'Must be in format YYYY-MM-DD')
							.required('Required'),
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
						<Form className="form">
							<div className={'form-row' + (errors.firstName && touched.firstName ? ' error' : '')}>
								<label htmlFor="firstName">First name*</label>
								<Field id="firstName" name="firstName" type="text" />
								<ErrorMessage name="firstName" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.lastName && touched.lastName ? ' error' : '')}>
								<label htmlFor="lastName">Last name*</label>
								<Field id="lastName" name="lastName" type="text" />
								<ErrorMessage name="lastName" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.birthDate && touched.birthDate ? ' error' : '')}>
								<label htmlFor="birthDate">Birth date*</label>
								<Field id="birthDate" name="birthDate" type="text" placeholder="YYYY-MM-DD" />
								<ErrorMessage name="birthDate" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.address && touched.address ? ' error' : '')}>
								<label htmlFor="address">Address*</label>
								<Field id="address" name="address" type="text" />
								<ErrorMessage name="address" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.phoneNumber && touched.phoneNumber ? ' error' : '')}>
								<label htmlFor="phoneNumber">Phone number*</label>
								<Field id="phoneNumber" name="phoneNumber" type="text" />
								<ErrorMessage name="phoneNumber" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.email && touched.email ? ' error' : '')}>
								<label htmlFor="email">Email</label>
								<Field id="email" name="email" type="email" />
								<ErrorMessage name="email" component="div" className="error" />
							</div>
							{ alert.add && 
								<div className="alert-box">
									<span>{alert.add}</span>
									<button className="close-btn" type="button" onClick={e => clearAlert()}>X</button>
								</div>
							}
							<div className="form-row btn">
								<button className={'submit-btn' + (isAdding ? ' loading' : '')} type="submit" disabled={isSubmitting}>
									Add patient
								</button>
							</div>
						</Form>		
					)}					
				</Formik>
			</React.Fragment>
		);
	}
}

export default PatientAddForm;