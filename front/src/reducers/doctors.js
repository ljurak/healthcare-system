import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const byId = (state = {}, action) => {
	switch (action.type) {
		case actions.FETCH_DOCTOR_SUCCESS:
		case actions.FETCH_DOCTORS_SUCCESS:
		case actions.ADD_DOCTOR_SUCCESS:
		case actions.UPDATE_DOCTOR_SUCCESS:
			return {
				...state,
				...action.payload.entities.doctors
			};
		default:
			return state;
	}
};

const visibleIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_DOCTORS_SUCCESS:
			return action.payload.result;
		default:
			return state;
	}
};

const requestInfo = (state = { isFetching: false, isAdding: false, isUpdating: false }, action) => {
	switch (action.type) {
		case actions.FETCH_DOCTOR_REQUEST:
		case actions.FETCH_DOCTORS_REQUEST:
			return {
				...state,
				isFetching: true
			};
		case actions.FETCH_DOCTOR_SUCCESS:
		case actions.FETCH_DOCTORS_SUCCESS:
		case actions.FETCH_DOCTOR_FAILURE:
		case actions.FETCH_DOCTORS_FAILURE:
			return {
				...state,
				isFetching: false
			};
		case actions.ADD_DOCTOR_REQUEST:
			return {
				...state,
				isAdding: true
			};
		case actions.ADD_DOCTOR_SUCCESS:
		case actions.ADD_DOCTOR_FAILURE:
			return {
				...state,
				isAdding: false
			};
		case actions.UPDATE_DOCTOR_REQUEST:
			return {
				...state,
				isUpdating: true
			};
		case actions.UPDATE_DOCTOR_SUCCESS:
		case actions.UPDATE_DOCTOR_FAILURE:
			return {
				...state,
				isUpdating: false
			};
		default:
			return state;
	}
};

export const getVisibleIds = (state) => state.visibleIds;

export const getDoctor = (state, id) => state.byId[id];

export const getVisibleDoctors = (state) => state.visibleIds.map(id => getDoctor(state, id));

export default combineReducers({
	byId,
	visibleIds,
	requestInfo
});
