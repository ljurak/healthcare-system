import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const byId = (state = {}, action) => {
	switch (action.type) {
		case actions.FETCH_SPECIALTIES_SUCCESS:
			return {
				...state,
				...action.payload.entities.specialties
			};
		case actions.LOGOUT:
			return {};
		default:
			return state;
	}
};

const visibleIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_SPECIALTIES_SUCCESS:
			return action.payload.result;
		case actions.LOGOUT:
			return [];
		default:
			return state;
	}
};

const requestInfo = (state = { isFetching: false }, action) => {
	switch (action.type) {
		case actions.FETCH_SPECIALTIES_REQUEST:
			return {
				...state,
				isFetching: true
			};
		case actions.FETCH_SPECIALTIES_SUCCESS:
		case actions.FETCH_SPECIALTIES_FAILURE:
			return {
				...state,
				isFetching: false
			};
		default:
			return state;
	}
};

export const getVisibleIds = (state) => state.visibleIds;

export const getSpecialty = (state, id) => state.byId[id];

export const getVisibleSpecialties = (state) => state.visibleIds.map(id => getSpecialty(state, id));

export const getIsFetching = (state) => state.requestInfo.isFetching;

export default combineReducers({
	byId,
	visibleIds,
	requestInfo
});