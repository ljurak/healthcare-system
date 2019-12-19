import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

class LoginPage extends React.Component {

	render() {
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
					onSubmit={() => {}}>
					
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
								<button className="submit-btn" type="submit" disabled={isSubmitting}>
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

export default LoginPage;