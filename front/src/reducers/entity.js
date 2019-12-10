import { combineReducers } from 'redux';

const entity = (types, key) => {
	if (!Array.isArray(types) || types.length !== 3) {
		throw new Error('Expected types to be array of 3 elements')
	}

	if (!types.every(t => typeof t === 'string')) {
		throw new Error('Expected types to be strings');
	}

	const [ requestAction, successAction, failureAction ] = types;

	const byId = (state = {}, action) => {
		switch (action.type) {
			case successAction:
				return {
					...state,
					...action.payload.entities[key]
				};
			default:
				return state;
		}
	};

	const allIds = (state = [], action) => {
		switch (action.type) {
			case successAction:
				return [
					...state,
					...action.payload.result
				];
			default:
				return state;
		}
	};

	const visibleIds = (state = [], action) => {
		switch (action.type) {
			case successAction:
				return action.payload.result;
			default:
				return state;
		}
	};

	return combineReducers({
		byId,
		allIds,
		visibleIds
	});
};

export default entity;