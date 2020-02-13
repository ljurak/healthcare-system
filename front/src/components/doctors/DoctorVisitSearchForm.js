import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import * as moment from 'moment';

import DatePickerField from './DoctorVisitSearchFormDatePickerField';

class DoctorVisitSearchForm extends React.Component {

	handleSubmit = (values, actions) => {
		const doctorId = this.props.doctorId;
		const startDate = moment(values.startDate).format('YYYY-MM-DD');
		const endDate = moment(values.endDate).format('YYYY-MM-DD');

		this.props.fetchVisits(doctorId, startDate, endDate)
			.finally(() => {
				actions.setSubmitting(false);
				actions.resetForm();
			});
	}

	render() {
		const { isFetching } = this.props;
		return (
			<React.Fragment>
				<h3 className="visit-search-form-title">Search visits</h3>
				<Formik
					initialValues={{ from: '', to: '' }}
					validationSchema={Yup.object({
						startDate: Yup.date()
							.required('Required').nullable(),
						endDate: Yup.date()
							.required('Required').nullable()
							.min(Yup.ref('startDate'), 'End date must be after start date'),
					})}
					onSubmit={this.handleSubmit}>
					
					{({ values, errors, touched, setFieldValue, handleBlur, handleChange, isSubmitting }) => (						
						<Form className="visit-search-form">
							<div className={'form-row' + (errors.startDate && touched.startDate ? ' error' : '')}>
								<label htmlFor="startDate">Start date*</label>									
								<DatePickerField 
									id="startDate" 
									name="startDate" 
									value={values.startDate} 
									onChange={setFieldValue} 
									onBlur={handleBlur} 
								/>
								<ErrorMessage name="startDate" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.endDate && touched.endDate ? ' error' : '')}>
								<label htmlFor="endDate">End date*</label>
								<DatePickerField 
									id="endDate" 
									name="endDate" 
									value={values.endDate} 
									onChange={setFieldValue} 
									onBlur={handleBlur} 
								/>
								<ErrorMessage name="endDate" component="div" className="error" />
							</div>								
							<div className="form-row btn">
								<button className={'submit-btn' + (isFetching ? ' loading' : '')} type="submit" disabled={isSubmitting}>
									Search
								</button>
							</div>
						</Form>							
					)}					
				</Formik>
			</React.Fragment>
		);
	}
}

export default DoctorVisitSearchForm;