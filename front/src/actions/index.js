import { normalize } from 'normalizr';

import * as actions from './actionTypes';
import * as schema from './schema';
import { PatientsApi, DoctorsApi, VisitsApi, SpecialtiesApi, LoginApi } from '../api';
import { getPatient } from '../reducers';

// ACTION CREATORS

const makeRequestActionCreator = (type) => () => ({	type });

const fetchPatientsRequest = makeRequestActionCreator(actions.FETCH_PATIENTS_REQUEST);
const fetchPatientRequest = makeRequestActionCreator(actions.FETCH_PATIENT_REQUEST);
const fetchDoctorsRequest = makeRequestActionCreator(actions.FETCH_DOCTORS_REQUEST);
const fetchDoctorRequest = makeRequestActionCreator(actions.FETCH_DOCTOR_REQUEST);
const fetchSpecialtiesRequest = makeRequestActionCreator(actions.FETCH_SPECIALTIES_REQUEST);
const addPatientRequest = makeRequestActionCreator(actions.ADD_PATIENT_REQUEST);
const updatePatientRequest = makeRequestActionCreator(actions.UPDATE_PATIENT_REQUEST);
const addVisitRequest = makeRequestActionCreator(actions.ADD_VISIT_REQUEST);
const loginRequest = makeRequestActionCreator(actions.LOGIN_REQUEST);

const makeSuccessActionCreator = (type) => (payload) => ({ type, payload });

const fetchPatientsSuccess = makeSuccessActionCreator(actions.FETCH_PATIENTS_SUCCESS);
const fetchPatientSuccess = makeSuccessActionCreator(actions.FETCH_PATIENT_SUCCESS);
const fetchDoctorsSuccess = makeSuccessActionCreator(actions.FETCH_DOCTORS_SUCCESS);
const fetchDoctorSuccess = makeSuccessActionCreator(actions.FETCH_DOCTOR_SUCCESS);
const fetchSpecialtiesSuccess = makeSuccessActionCreator(actions.FETCH_SPECIALTIES_SUCCESS);
const addPatientSuccess = makeSuccessActionCreator(actions.ADD_PATIENT_SUCCESS);
const updatePatientSuccess = makeSuccessActionCreator(actions.UPDATE_PATIENT_SUCCESS);
const addVisitSuccess = makeSuccessActionCreator(actions.ADD_VISIT_SUCCESS);
const loginSuccess = makeSuccessActionCreator(actions.LOGIN_SUCCESS);

const makeFailureActionCreator = (type) => (payload) => ({ type, payload, error: true});

const fetchPatientsFailure = makeFailureActionCreator(actions.FETCH_PATIENTS_FAILURE);
const fetchPatientFailure = makeFailureActionCreator(actions.FETCH_PATIENT_FAILURE);
const fetchDoctorsFailure = makeFailureActionCreator(actions.FETCH_DOCTORS_FAILURE);
const fetchDoctorFailure = makeFailureActionCreator(actions.FETCH_DOCTOR_FAILURE);
const fetchSpecialtiesFailure = makeFailureActionCreator(actions.FETCH_SPECIALTIES_FAILURE);
const addPatientFailure = makeFailureActionCreator(actions.ADD_PATIENT_FAILURE);
const updatePatientFailure = makeFailureActionCreator(actions.UPDATE_PATIENT_FAILURE);
const addVisitFailure = makeFailureActionCreator(actions.ADD_VISIT_FAILURE);
const loginFailure = makeFailureActionCreator(actions.LOGIN_FAILURE);

export const logout = () => {
	localStorage.removeItem('token');
	return { type: actions.LOGOUT };
};

// ASYNC ACTION CREATORS

// PATIENTS
export const fetchPatients = () => (dispatch) => {
	dispatch(fetchPatientsRequest());

	return PatientsApi.fetchPatients()
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchPatientsSuccess(normalize(response, schema.patientsListSchema))), 
			error => dispatch(fetchPatientsFailure(error))
		);
};

export const fetchPatientsByLastname = (lastname) => (dispatch) => {
	dispatch(fetchPatientsRequest());

	return PatientsApi.fetchPatientsByLastname(lastname)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchPatientsSuccess(normalize(response, schema.patientsListSchema))),
			error => dispatch(fetchPatientsFailure(error))
		);
};

export const fetchPatientById = (id) => (dispatch, getState) => {
	if (getPatient(getState(), id)) {
		return Promise.resolve();
	}

	dispatch(fetchPatientRequest());

	return PatientsApi.fetchPatientById(id)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchPatientSuccess(normalize([ response ], schema.patientsListSchema))),
			error => dispatch(fetchPatientFailure(error))
		);
};

export const addPatient = (patient) => (dispatch) => {
	dispatch(addPatientRequest());

	return PatientsApi.addPatient(patient)
		.then(handleApiResponse)
		.then(
			response => dispatch(addPatientSuccess(normalize([ response ], schema.patientsListSchema))),
			error => dispatch(addPatientFailure(error))
		);
};

export const updatePatient = (patient, id) => (dispatch) => {
	dispatch(updatePatientRequest());

	return PatientsApi.updatePatient(patient, id)
		.then(handleApiResponse)
		.then(
			response => dispatch(updatePatientSuccess(normalize([ response ], schema.patientsListSchema))),
			error => dispatch(updatePatientFailure(error))
		);
};

export const addVisit = (visit, patientId) => (dispatch) => {
	dispatch(addVisitRequest());

	return VisitsApi.addVisit(visit, patientId)
		.then(handleApiResponse)
		.then(
			response => dispatch(addVisitSuccess(normalize([ response ], schema.visitsListSchema))),
			error => dispatch(addVisitFailure(error))
		);
};

// DOCTORS

export const fetchDoctors = () => (dispatch) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctors()
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
		);
};

export const fetchDoctorsByLastname = (lastname) => (dispatch) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctorsByLastname(lastname)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
		);
};

export const fetchDoctorsBySpecialty = (specialty) => (dispatch) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctorsBySpecialty(specialty)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
		);
};

export const fetchDoctorById = (id) => (dispatch, getState) => {
	dispatch(fetchDoctorRequest());

	return DoctorsApi.fetchDoctorById(id)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchDoctorSuccess(normalize([ response ], schema.doctorsListSchema))),
			error => dispatch(fetchDoctorFailure(error))
		);
};

// SPECIALTIES

export const fetchSpecialties = () => (dispatch, getState) => {
	if (getState().entities.specialties.visibleIds.length) {
		return Promise.resolve();
	}

	dispatch(fetchSpecialtiesRequest());

	return SpecialtiesApi.fetchSpecialties()
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchSpecialtiesSuccess(normalize(response, schema.specialtiesListSchema))),
			error => dispatch(fetchSpecialtiesFailure(error))
		);
};


export const login = (user) => (dispatch) => {
	dispatch(loginRequest());

	return LoginApi.login(user)
		.then(response => {
			if (response.ok) {
				return response.json();
			}

			throw new Error('Incorrect username or password');
		})
		.then(
			response => {
				localStorage.setItem('token', response.token);
				dispatch(loginSuccess(response));
			}, 
			error => dispatch(loginFailure({ message: error.message }))
		);
};

// helper function processing response from api
const handleApiResponse = (response) => {
	return response.json().then(json => {
		if (!response.ok) {
			return Promise.reject(json);
		}

		return json;
	});
};
