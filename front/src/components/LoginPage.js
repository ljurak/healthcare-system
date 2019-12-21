import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { connect } from 'react-redux';
import * as Yup from 'yup';

import { login, logout } from '../actions';

class LoginPage extends React.Component {
	constructor(props) {
		super(props);
		this.props.logout();
	}

	handleSubmit = (values, actions) => {
		const user = { ...values };

		this.props.login(user)
			.finally(() => {
				actions.setSubmitting(false);
				actions.resetForm();
			});
	}

	render() {
		const { isLogging } = this.props;
		return (
			<div className="login-form-wrapper">
				<h3 className="login-form-title">Sign in</h3>
				<Formik
					initialValues={{ username: '', password: ''}}
					validationSchema={Yup.object({
						username: Yup.string()
							.min(3, 'Must be 3 characters or more')
							.max(40, 'Must be 40 characters or less')
							.required('Required'),
						password: Yup.string()
							.min(3, 'Must be 3 characters or more')
							.max(40, 'Must be 40 characters or less')
							.required('Required')
					})}
					onSubmit={this.handleSubmit}>
					
					{({ errors, touched, isSubmitting }) => (
						<Form className="form">
							<div className={'form-row' + (errors.username && touched.username ? ' error' : '')}>
								<label htmlFor="username">Username*</label>
								<Field id="username" name="username" type="text" />
								<ErrorMessage name="username" component="div" className="error" />
							</div>
							<div className={'form-row' + (errors.password && touched.password ? ' error' : '')}>
								<label htmlFor="password">Password*</label>
								<Field id="password" name="password" type="password" />
								<ErrorMessage name="password" component="div" className="error" />
							</div>
							<div className="form-row btn">
								<button className={'submit-btn' + (isLogging ? ' loading' : '')} type="submit" disabled={isSubmitting}>
									Sign in
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
		isLogging: state.authentication.isLogging
	};
};

const mapDispatchToProps = { login, logout };

export default connect(
	mapStateToProps, 
	mapDispatchToProps)
(LoginPage);