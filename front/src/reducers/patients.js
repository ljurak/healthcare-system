import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const byId = (state = {}, action) => {
	switch (action.type) {
		case actions.FETCH_PATIENT_SUCCESS:
		case actions.FETCH_PATIENTS_SUCCESS:
		case actions.ADD_PATIENT_SUCCESS:
		case actions.UPDATE_PATIENT_SUCCESS:
			return {
				...state,
				...action.payload.entities.patients
			};
		case actions.LOGOUT:
			return {};
		default:
			return state;
	}
};

const visibleIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_PATIENTS_SUCCESS:
			return action.payload.result;
		case actions.LOGOUT:
			return [];
		default:
			return state;
	}
};

const requestInfo = (state = { isFetching: false, isAdding: false, isUpdating: false }, action) => {
	switch (action.type) {
		case actions.FETCH_PATIENT_REQUEST:
		case actions.FETCH_PATIENTS_REQUEST:
			return {
				...state,
				isFetching: true
			};
		case actions.FETCH_PATIENT_SUCCESS:
		case actions.FETCH_PATIENTS_SUCCESS:
		case actions.FETCH_PATIENT_FAILURE:
		case actions.FETCH_PATIENTS_FAILURE:
			return {
				...state,
				isFetching: false
			};
		case actions.ADD_PATIENT_REQUEST:
			return {
				...state,
				isAdding: true
			};
		case actions.ADD_PATIENT_SUCCESS:
		case actions.ADD_PATIENT_FAILURE:
			return {
				...state,
				isAdding: false
			};
		case actions.UPDATE_PATIENT_REQUEST:
			return {
				...state,
				isUpdating: true
			};
		case actions.UPDATE_PATIENT_SUCCESS:
		case actions.UPDATE_PATIENT_FAILURE:
			return {
				...state,
				isUpdating: false
			};
		default:
			return state;
	}
};

export const getVisibleIds = (state) => state.visibleIds;

export const getPatient = (state, id) => state.byId[id];

export const getVisiblePatients = (state) => state.visibleIds.map(id => getPatient(state, id));

export const getIsFetching = (state) => state.requestInfo.isFetching;

export const getIsAdding = (state) => state.requestInfo.isAdding;

export const getIsUpdating = (state) => state.requestInfo.isUpdating;

export default combineReducers({
	byId,
	visibleIds,
	requestInfo
});

