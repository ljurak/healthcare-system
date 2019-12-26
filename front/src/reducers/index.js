import { combineReducers } from 'redux';

import patients from './patients';
import doctors from './doctors';
import specialties from './specialties';
import authentication from './authentication';
import alert from './alert';
import * as fromPatients from './patients';
import * as fromDoctors from './doctors';
import * as fromSpecialties from './specialties';
import * as fromAlert from './alert';

const entities = combineReducers({
	patients,
	doctors,
	specialties
});

const rootReducer = combineReducers({
	entities,
	authentication,
	alert
});

export const getVisiblePatients = (state) => fromPatients.getVisiblePatients(state.entities.patients);

export const getPatient = (state, id) => fromPatients.getPatient(state.entities.patients, id);

export const getVisibleDoctors = (state) => fromDoctors.getVisibleDoctors(state.entities.doctors);

export const getDoctor = (state, id) => fromDoctors.getDoctor(state.entities.doctors, id);

export const getVisibleSpecialties = (state) => fromSpecialties.getVisibleSpecialties(state.entities.specialties);

export const getSpecialty = (state, id) => fromSpecialties.getSpecialty(state.entities.specialties, id);

export const getIsFetchingPatients = (state) => fromPatients.getIsFetching(state.entities.patients);

export const getIsAddingPatient = (state) => fromPatients.getIsAdding(state.entities.patients);

export const getIsUpdatingPatient = (state) => fromPatients.getIsUpdating(state.entities.patients);

export const getIsFetchingDoctors = (state) => fromDoctors.getIsFetching(state.entities.doctors);

export const getIsAddingDoctor = (state) => fromDoctors.getIsAdding(state.entities.doctors);

export const getIsUpdatingDoctor = (state) => fromDoctors.getIsUpdating(state.entities.doctors);

export const getIsFetchingSpecialties = (state) => fromSpecialties.getIsFetching(state.entities.specialties);

export const getPatientsAlert = (state) => fromAlert.getPatientsAlert(state.alert);

export const getDoctorsAlert = (state) => fromAlert.getDoctorsAlert(state.alert);

export default rootReducer;