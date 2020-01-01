import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';

const byId = (state = {}, action) => {
	switch (action.type) {
		case actions.FETCH_VISITS_SUCCESS:
		case actions.ADD_VISIT_SUCCESS:
		case actions.UPDATE_VISIT_SUCCESS:
			return {
				...state,
				...action.payload.entities.visits
			};
		default:
			return state;
	}
};

const visibleIds = (state = [], action) => {
	switch (action.type) {
		case actions.FETCH_VISITS_SUCCESS:
			return action.payload.result;
		case actions.ADD_VISIT_SUCCESS:
			return [
				action.payload.result,
				...state
				
			];
		default:
			return state;
	}	
};

const requestInfo = (state = { isFetching: false, isAdding: false, isUpdating: false }, action) => {
	switch (action.type) {
		case actions.FETCH_VISITS_REQUEST:
			return {
				...state,
				isFetching: true
			};
		case actions.FETCH_VISITS_SUCCESS:
		case actions.FETCH_VISITS_FAILURE:
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

export const getVisibleIds = (state) => state.visibleIds;

export const getVisit = (state, id) => state.byId[id];

export const getVisibleVisits = (state) => state.visibleIds.map(id => getVisit(state, id));

export const getIsFetching = (state) => state.requestInfo.isFetching;

export const getIsAdding = (state) => state.requestInfo.isAdding;

export const getIsUpdating = (state) => state.requestInfo.isUpdating;

export default combineReducers({
	byId,
	visibleIds,
	requestInfo
});