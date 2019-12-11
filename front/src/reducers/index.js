import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';
import entity from './entity';
import * as fromEntities from './entity';

const entities = combineReducers({
	patients: entity([ actions.FETCH_PATIENTS_REQUEST, actions.FETCH_PATIENTS_SUCCESS, actions.FETCH_PATIENTS_FAILURE ], 'patients'),
	doctors: entity([ actions.FETCH_DOCTORS_REQUEST, actions.FETCH_DOCTORS_SUCCESS, actions.FETCH_DOCTORS_FAILURE ], 'doctors')
});

const rootReducer = combineReducers({
	entities
});

export const getVisiblePatients = (state) => fromEntities.getVisibleEntities(state.entities.patients);

export default rootReducer;