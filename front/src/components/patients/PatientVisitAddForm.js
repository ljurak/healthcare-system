import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import * as moment from 'moment';

import DatePickerField from '../DatePickerField';
import TimePickerField from '../TimePickerField';
import DoctorsField from './DoctorsField';

class PatientVisitAddForm extends React.Component {

	handleSubmit = (values, actions) => {
		const patientId = this.props.patientId;
		
		const visit = {};
		visit.patientId = patientId;
		visit.doctorId = values.doctor;
		visit.visitDate = moment(values.visitDate).format('YYYY-MM-DD');
		visit.visitTime = moment(values.visitTime).format('HH:mm');

		this.props.addVisit(visit, patientId)
			.finally(() => {
				actions.setSubmitting(false);
				actions.resetForm();
			});
	}

	render() {
		const { isAdding } = this.props;
		return (
			<React.Fragment>
				<h3 className="patient-visit-form-title">Add new visit</h3>
				<Formik
					initialValues={{ visitDate: '', visitTime: '', specialty: '', doctor: ''}}
					validationSchema={Yup.object({
						visitDate: Yup.date()
							.required('Required').nullable(),
						visitTime: Yup.date()
							.required('Required').nullable()
							.test('is-in-range', 'Time out of range', function(value) {
								return moment(value).isBetween(moment('08:00', 'HH:mm'), moment('15:00', 'HH:mm'), 'hour', '[]');
							}),
						doctor: Yup.number()
							.required('Required')
					})}
					onSubmit={this.handleSubmit}>
					
					{({ values, errors, touched, setFieldValue, handleBlur, handleChange, isSubmitting }) => {
						const handleSpecialtyChange = (e) => {
							this.props.fetchDoctors(e.target.value);
							handleChange(e);
						};

						return (
							<Form className="add-visit-form">
								<div className={'form-row' + (errors.visitDate && touched.visitDate ? ' error' : '')}>
									<label htmlFor="date">Visit date*</label>
									<div className="form-field">
										<DatePickerField id="visitDate" name="visitDate" value={values.visitDate} onChange={setFieldValue} onBlur={handleBlur} />
										<ErrorMessage name="visitDate" component="div" className="error" />
									</div>
								</div>
								<div className={'form-row' + (errors.visitTime && touched.visitTime ? ' error' : '')}>
									<label htmlFor="date">Visit time*</label>
									<div className="form-field">
										<TimePickerField id="visitTime" name="visitTime" value={values.visitTime} onChange={setFieldValue} onBlur={handleBlur} />
										<ErrorMessage name="visitTime" component="div" className="error" />
									</div>
								</div>								
								<div className="form-row">
									<label htmlFor="specialty">Doctor's specialty</label>
									<div className="form-field">
										<Field id="specialty" name="specialty" as="select" onChange={handleSpecialtyChange}>
											<option value="">-- Choose a specialty --</option>
											{this.props.specialties.map(specialty => (
												<option key={specialty.id} value={specialty.name}>{specialty.name}</option>
											))}
										</Field>
									</div>
								</div>
								<div className={'form-row' + (errors.doctor && touched.doctor ? ' error' : '')}>
									<label htmlFor="doctor">Doctor*</label>
									<div className="form-field">
										<Field id="doctor" name="doctor" component={DoctorsField} />
										<ErrorMessage name="doctor" component="div" className="error" />
									</div>
								</div>								
								<div className="form-row btn">
									<button className={'submit-btn' + (isAdding ? ' loading' : '')} type="submit" disabled={isSubmitting}>
										Add visit
									</button>
								</div>
							</Form>
						);	
					}}					
				</Formik>
			</React.Fragment>
		);
	}
}

export default PatientVisitAddForm;