import React from 'react';
import { connect } from 'react-redux';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

import { getIsUpdatingVisit, getVisitsAlert } from '../reducers';
import { updateVisit, clearAlert } from '../actions';

class VisitEditForm extends React.Component {
	
	handleSubmit = (values, actions) => {
		const visit = { ...this.props.visit };

		visit.description = values.description;
		visit.status = values.status;

		this.props.updateVisit(visit, visit.patientId)
			.finally(() => {
				actions.setSubmitting(false);
			});
	}

	render() {
		const { visit, isUpdating, alert, clearAlert } = this.props;
		if (!visit) {
			return <div>Loading...</div>;
		}

		let { patientName, doctorName, visitDate, visitTime, description, status } = visit;
		if (!description) {
			description = '';
		}

		return (
			<div className="visit-list-item-details">
				<Formik
					enableReinitialize={true}
					initialValues={{ patientName, doctorName, visitDate, visitTime, description, status }}
					validationSchema={Yup.object({
						description: Yup.string()
							.max(4000, 'Must be 4000 characters or less'),
						status: Yup.string()
							.required('Required')
					})}
					onSubmit={this.handleSubmit}>
					
					{({ errors, touched, isSubmitting }) => (
						<Form className="visit-update-form">
							<div className="form-row">
								<label htmlFor="patientName">Patient</label>
								<Field id="patientName" name="patientName" type="text" disabled={true} />								
							</div>
							<div className="form-row">
								<label htmlFor="doctorId">Doctor</label>								
								<Field id="doctorName" name="doctorName" type="text" disabled={true} />								
							</div>
							<div className="form-row">
								<label htmlFor="visitDate">Visit date</label>								
								<Field id="visitDate" name="visitDate" type="text" disabled={true} />								
							</div>
							<div className="form-row">
								<label htmlFor="visitTime">Visit time</label>								
								<Field id="visitTime" name="visitTime" type="text" disabled={true} />								
							</div>
							<div className={'form-row' + (errors.description && touched.description ? ' error' : '')}>
								<label htmlFor="description">Description</label>								
								<Field id="description" name="description" as="textarea" />
								<ErrorMessage name="description" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.status && touched.status ? ' error' : '')}>
								<label htmlFor="status">Status*</label>
								<Field id="status" name="status" as="select">
									<option value="">-- Choose a status --</option>
									<option value="ACTIVE">Active</option>
									<option value="COMPLETED">Completed</option>
									<option value="CANCELLED">Cancelled</option>
								</Field>
								<ErrorMessage name="status" component="div" className="error" />
							</div>
							{ alert.update && 
								<div className={`alert-box ${alert.type}`}>
									<span>{alert.update}</span>
									<button className="close-btn" type="button" onClick={e => clearAlert()}>X</button>
								</div>
							}
							<div className="form-row btn">
								<button className={'submit-btn' + (isUpdating ? ' loading' : '')} type="submit" disabled={isSubmitting}>
									Update visit							
								</button>
							</div>
						</Form>
					)}					
				</Formik>
			</div>
		);
	}
};

const mapStateToProps = (state) => {
	return {
		isUpdating: getIsUpdatingVisit(state),
		alert: getVisitsAlert(state)
	};
};

const mapDispatchToProps = { updateVisit, clearAlert };

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(VisitEditForm);