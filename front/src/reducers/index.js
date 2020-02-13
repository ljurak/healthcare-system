import { combineReducers } from 'redux';

import patients from './patients';
import doctors from './doctors';
import specialties from './specialties';
import visits from './visits';
import authentication from './authentication';
import alert from './alert';
import * as fromPatients from './patients';
import * as fromDoctors from './doctors';
import * as fromSpecialties from './specialties';
import * as fromVisits from './visits';
import * as fromAlert from './alert';

const entities = combineReducers({
	patients,
	doctors,
	specialties,
	visits
});

const rootReducer = combineReducers({
	entities,
	authentication,
	alert
});

export const getVisiblePatients = (state) => fromPatients.getVisiblePatients(state.entities.patients);

export const getPatient = (state, id) => fromPatients.getPatient(state.entities.patients, id);

export const getVisibleDoctors = (state) => fromDoctors.getVisibleDoctors(state.entities.doctors);

export const getVisibleDoctorsBySpecialty = (state) => fromDoctors.getVisibleDoctorsBySpecialty(state.entities.doctors);

export const getDoctor = (state, id) => fromDoctors.getDoctor(state.entities.doctors, id);

export const getVisibleSpecialties = (state) => fromSpecialties.getVisibleSpecialties(state.entities.specialties);

export const getSpecialty = (state, id) => fromSpecialties.getSpecialty(state.entities.specialties, id);

export const getVisiblePatientVisits = (state) => fromVisits.getVisiblePatientVisits(state.entities.visits);

export const getVisibleDoctorVisits = (state) => fromVisits.getVisibleDoctorVisits(state.entities.visits);

export const getVisibleSchedulerVisits = (state) => fromVisits.getVisibleSchedulerVisits(state.entities.visits);

export const getVisit = (state, id) => fromVisits.getVisit(state.entities.visits, id);

export const getIsFetchingPatients = (state) => fromPatients.getIsFetching(state.entities.patients);

export const getIsAddingPatient = (state) => fromPatients.getIsAdding(state.entities.patients);

export const getIsUpdatingPatient = (state) => fromPatients.getIsUpdating(state.entities.patients);

export const getIsFetchingDoctors = (state) => fromDoctors.getIsFetching(state.entities.doctors);

export const getIsAddingDoctor = (state) => fromDoctors.getIsAdding(state.entities.doctors);

export const getIsUpdatingDoctor = (state) => fromDoctors.getIsUpdating(state.entities.doctors);

export const getIsFetchingVisits = (state) => fromVisits.getIsFetching(state.entities.visits);

export const getIsAddingVisit = (state) => fromVisits.getIsAdding(state.entities.visits);

export const getIsUpdatingVisit = (state) => fromVisits.getIsUpdating(state.entities.visits);

export const getIsFetchingSpecialties = (state) => fromSpecialties.getIsFetching(state.entities.specialties);

export const getPatientsAlert = (state) => fromAlert.getPatientsAlert(state.alert);

export const getDoctorsAlert = (state) => fromAlert.getDoctorsAlert(state.alert);

export const getVisitsAlert = (state) => fromAlert.getVisitsAlert(state.alert);

export const getAuthenticationAlert = (state) => fromAlert.getAuthenticationAlert(state.alert);

export default rootReducer;