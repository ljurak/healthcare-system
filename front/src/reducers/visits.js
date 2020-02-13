import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const byId = (state = {}, action) => {
	switch (action.type) {
		case actions.FETCH_PATIENT_VISITS_SUCCESS:
		case actions.FETCH_DOCTOR_VISITS_SUCCESS:
		case actions.FETCH_SCHEDULER_VISITS_SUCCESS:
		case actions.ADD_VISIT_SUCCESS:
		case actions.UPDATE_VISIT_SUCCESS:
			return {
				...state,
				...action.payload.entities.visits
			};
		case actions.LOGOUT:
			return {};
		default:
			return state;
	}
};

const visiblePatientIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_PATIENT_VISITS_SUCCESS:
			return action.payload.result;
		case actions.ADD_VISIT_SUCCESS:
			return [
				action.payload.result,
				...state
			];
		case actions.LOGOUT:
			return [];
		default:
			return state;
	}	
};

const visibleDoctorIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_DOCTOR_VISITS_SUCCESS:
			return action.payload.result;
		case actions.LOGOUT:
			return [];
		default:
			return state;
	}	
};

const visibleSchedulerIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_SCHEDULER_VISITS_SUCCESS:
			return action.payload.result;
		case actions.CLEAR_VISITS:
		case actions.LOGOUT:
			return [];
		default:
			return state;
	}
};

const requestInfo = (state = { isFetching: false, isAdding: false, isUpdating: false }, action) => {
	switch (action.type) {
		case actions.FETCH_PATIENT_VISITS_REQUEST:
		case actions.FETCH_DOCTOR_VISITS_REQUEST:
			return {
				...state,
				isFetching: true
			};
		case actions.FETCH_PATIENT_VISITS_SUCCESS:
		case actions.FETCH_DOCTOR_VISITS_SUCCESS:
		case actions.FETCH_PATIENT_VISITS_FAILURE:
		case actions.FETCH_DOCTOR_VISITS_FAILURE:
			return {
				...state,
				isFetching: false
			};
		case actions.ADD_VISIT_REQUEST:
			return {
				...state,
				isAdding: true
			};
		case actions.ADD_VISIT_SUCCESS:
		case actions.ADD_VISIT_FAILURE:
			return {
				...state,
				isAdding: false
			};
		case actions.UPDATE_VISIT_REQUEST:
			return {
				...state,
				isUpdating: true
			};
		case actions.UPDATE_VISIT_SUCCESS:
		case actions.UPDATE_VISIT_FAILURE:
			return {
				...state,
				isUpdating: false
			};
		default:
			return state;
	}
};

export const getVisiblePatientIds = (state) => state.visiblePatientIds;

export const getVisibleDoctorIds = (state) => state.visibleDoctorIds;

export const getVisibleSchedulerIds = (state) => state.visibleSchedulerIds;

export const getVisit = (state, id) => state.byId[id];

export const getVisiblePatientVisits = (state) => state.visiblePatientIds.map(id => getVisit(state, id));

export const getVisibleDoctorVisits = (state) => state.visibleDoctorIds.map(id => getVisit(state, id));

export const getVisibleSchedulerVisits = (state) => state.visibleSchedulerIds.map(id => getVisit(state, id));

export const getIsFetching = (state) => state.requestInfo.isFetching;

export const getIsAdding = (state) => state.requestInfo.isAdding;

export const getIsUpdating = (state) => state.requestInfo.isUpdating;

export default combineReducers({
	byId,
	visiblePatientIds,
	visibleDoctorIds,
	visibleSchedulerIds,
	requestInfo
});