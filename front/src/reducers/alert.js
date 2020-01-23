import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const patients = (state = {}, action) => {
	switch (action.type) {
		case actions.ADD_PATIENT_SUCCESS:
			return {
				add: 'Successfully added new patient',
				type: 'alert-success'
			};
		case actions.ADD_PATIENT_FAILURE:
			return {
				add: action.payload.message,
				type: 'alert-failure'
			};
		case actions.UPDATE_PATIENT_SUCCESS:
			return {
				update: 'Successfully updated patient',
				type: 'alert-success'
			};
		case actions.UPDATE_PATIENT_FAILURE:
			return {
				update: action.payload.message,
				type: 'alert-failure'
			};
		case actions.ADD_PATIENT_REQUEST:
		case actions.UPDATE_PATIENT_REQUEST:
		case actions.CLEAR_ALERT:
			return {};
		default:
			return state;
	}
};

const doctors = (state = {}, action) => {
	switch (action.type) {
		case actions.ADD_DOCTOR_SUCCESS:
			return {
				add: 'Successfully added new doctor',
				type: 'alert-success'
			};
		case actions.ADD_DOCTOR_FAILURE:
			return {
				add: action.payload.message,
				type: 'alert-failure'
			};
		case actions.UPDATE_DOCTOR_SUCCESS:
			return {
				update: 'Successfully updated doctor',
				type: 'alert-success'
			};
		case actions.UPDATE_DOCTOR_FAILURE:
			return {
				update: action.payload.message,
				type: 'alert-failure'
			};
		case actions.ADD_DOCTOR_REQUEST:
		case actions.UPDATE_DOCTOR_REQUEST:
		case actions.CLEAR_ALERT:
			return {};
		default:
			return state;
	}
};

const visits = (state = {}, action) => {
	switch (action.type) {
		case actions.ADD_VISIT_SUCCESS:
			return {
				add: 'Successfully added new visit',
				type: 'alert-success'
			};
		case actions.ADD_VISIT_FAILURE:
			return {
				add: action.payload.message,
				type: 'alert-failure'
			};
		case actions.UPDATE_VISIT_SUCCESS:
			return {
				update: 'Successfully updated visit',
				visitId: action.payload.result,
				type: 'alert-success'
			};
		case actions.UPDATE_VISIT_FAILURE:
			return {
				update: action.payload.message,
				visitId: action.payload.visitId,
				type: 'alert-failure'
			};
		case actions.ADD_VISIT_REQUEST:
		case actions.UPDATE_VISIT_REQUEST:
		case actions.CLEAR_ALERT:
			return {};
		default:
			return state;
	}
};

const authentication = (state = {}, action) => {
	switch (action.type) {
		case actions.LOGIN_FAILURE:
			return {
				message: action.payload.message,
				type: 'alert-failure'
			};
		case actions.LOGIN_REQUEST:
		case actions.CLEAR_ALERT:
			return {};
		default:
			return state;
	}
};

export const getPatientsAlert = (state) => state.patients;

export const getDoctorsAlert = (state) => state.doctors;

export const getVisitsAlert = (state) => state.visits;

export const getAuthenticationAlert = (state) => state.authentication;

export default combineReducers({
	patients,
	doctors,
	visits,
	authentication
});