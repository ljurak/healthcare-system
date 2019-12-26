import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const patients = (state = {}, action) => {
	switch (action.type) {
		case actions.ADD_PATIENT_SUCCESS:
			return {
				add: 'Successfully added new patient'
			};
		case actions.ADD_PATIENT_FAILURE:
			return {
				add: 'Oups. Error occured. Patient wasn\'t added'
			};
		case actions.UPDATE_PATIENT_SUCCESS:
			return {
				update: 'Successfully updated patient'
			};
		case actions.UPDATE_PATIENT_FAILURE:
			return {
				update: 'Oups. Error occured. Patient wasn\'t updated'
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
				add: 'Successfully added new doctor'
			};
		case actions.ADD_DOCTOR_FAILURE:
			return {
				add: 'Oups. Error occured. Doctor wasn\'t added'
			};
		case actions.UPDATE_DOCTOR_SUCCESS:
			return {
				update: 'Successfully updated doctor'
			};
		case actions.UPDATE_DOCTOR_FAILURE:
			return {
				update: 'Oups. Error occured. Doctor wasn\'t updated'
			};
		case actions.ADD_DOCTOR_REQUEST:
		case actions.UPDATE_DOCTOR_REQUEST:
		case actions.CLEAR_ALERT:
			return {};
		default:
			return state;
	}
};

export const getPatientsAlert = (state) => state.patients;

export const getDoctorsAlert = (state) => state.doctors;

export default combineReducers({
	patients,
	doctors
});