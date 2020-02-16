import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import * as moment from 'moment';

import DatePickerField from './PatientVisitAddFormDatePickerField';
import TimePickerField from './PatientVisitAddFormTimePickerField';
import DoctorsField from './PatientVisitAddFormDoctorsField';
import Scheduler from '../visits/Scheduler';

class PatientVisitAddForm extends React.Component {

	handleSubmit = (values, actions) => {
		const patientId = this.props.patientId;
		
		const visit = {};
		visit.patientId = patientId;
		visit.doctorId = values.doctor;
		visit.visitDate = moment(values.visitDate).format('YYYY-MM-DD');
		visit.visitTime = moment(values.visitTime).format('HH:mm');
		visit.description = values.description;

		this.props.addVisit(visit, patientId)
			.finally(() => {
				actions.setSubmitting(false);
				actions.resetForm();
			});
	}

	render() {
		const { isAdding, alert, clearAlert } = this.props;
		return (
			<React.Fragment>
				<h3 className="patient-visit-form-title">Add new visit</h3>
				<Formik
					initialValues={{ visitDate: '', visitTime: '', specialty: '', doctor: '', description: ''}}
					validationSchema={Yup.object({
						visitDate: Yup.date()
							.required('Required').nullable(),
						visitTime: Yup.date()
							.required('Required').nullable()
							.test('is-in-range', 'Time out of range', function(value) {
								return moment(value).get('hour') >= 8 && moment(value).get('hour') <= 19;
							}),
						doctor: Yup.number()
							.required('Required'),
						description: Yup.string()
							.max(4000, 'Must be 4000 characters or less')
					})}
					onSubmit={this.handleSubmit}>
					
					{({ values, errors, touched, setFieldValue, handleChange, isSubmitting }) => {
						const handleSpecialtyChange = (e) => {
							this.props.fetchDoctors(e.target.value);
							setFieldValue('doctor', '');
							handleChange(e);
						};

						const handleDateTimeChange = (date) => {
							setFieldValue('visitDate', date);
							setFieldValue('visitTime', date);
						};

						return (
							<Form className="visit-add-form">
								<div className="form-row">
									<label htmlFor="specialty">Doctor's specialty</label>									
									<Field id="specialty" name="specialty" as="select" onChange={handleSpecialtyChange}>
										<option value="">-- Choose a specialty --</option>
										{this.props.specialties.map(specialty => (
											<option key={specialty.id} value={specialty.name}>{specialty.name}</option>
										))}
									</Field>					
								</div>
								<div className={'form-row' + (errors.doctor && touched.doctor ? ' error' : '')}>
									<label htmlFor="doctor">Doctor*</label>									
									<Field id="doctor" name="doctor" component={DoctorsField} />
									<ErrorMessage name="doctor" component="div" className="error" />									
								</div>
								<div className={'form-row' + (errors.visitDate && touched.visitDate ? ' error' : '')}>
									<label htmlFor="visitDate">Visit date*</label>
									<Field id="visitDate" name="visitDate" onChange={setFieldValue} component={DatePickerField} />						
									<ErrorMessage name="visitDate" component="div" className="error" />									
								</div>
								<div className={'form-row' + (errors.visitTime && touched.visitTime ? ' error' : '')}>
									<label htmlFor="visitTime">Visit time*</label>	
									<Field id="visitTime" name="visitTime" onChange={setFieldValue} component={TimePickerField} />							
									<ErrorMessage name="visitTime" component="div" className="error" />									
								</div>
								<Scheduler selectable={true} setDateTime={handleDateTimeChange} doctorId={values.doctor} />								
								<div className={'form-row' + (errors.description && touched.description ? ' error' : '')}>
									<label htmlFor="description">Description</label>									
									<Field id="description" name="description" as="textarea" />
									<ErrorMessage name="description" component="div" className="error" />									
								</div>
								{ alert.add && 
									<div className={`alert-box ${alert.type}`}>
										<span>{alert.add}</span>
										<button className="close-btn" type="button" onClick={e => clearAlert()}>X</button>
									</div>
								}						
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