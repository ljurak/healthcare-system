import { combineReducers } from 'redux';

import * as actions from '../actions/actionTypes';
import patients from './patients';
import doctors from './doctors';
import * as fromPatients from './patients';
import * as fromDoctors from './doctors';

const entities = combineReducers({
	patients,
	doctors
});

const rootReducer = combineReducers({
	entities
});

export const getVisiblePatients = (state) => fromPatients.getVisiblePatients(state.entities.patients);

export const getPatient = (state, id) => fromPatients.getPatient(state.entities.patients, id);

export const getVisibleDoctors = (state) => fromDoctors.getVisibleDoctors(state.entities.doctors);

export const getDoctor = (state, id) => fromDoctors.getDoctor(state.entities.doctors, id);

export default rootReducer;