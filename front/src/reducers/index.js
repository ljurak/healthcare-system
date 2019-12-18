import { combineReducers } from 'redux';

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

export const getIsFetchingPatients = (state) => fromPatients.getIsFetching(state.entities.patients);

export const getIsAddingPatient = (state) => fromPatients.getIsAdding(state.entities.patients);

export const getIsUpdatingPatient = (state) => fromPatients.getIsUpdating(state.entities.patients);

export const getIsFetchingDoctors = (state) => fromDoctors.getIsFetching(state.entities.doctors);

export const getIsAddingDoctor = (state) => fromDoctors.getIsAdding(state.entities.doctors);

export const getIsUpdatingDoctor = (state) => fromDoctors.getIsUpdating(state.entities.doctors);

export default rootReducer;