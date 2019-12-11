import { normalize } from 'normalizr';

import * as actions from './actionTypes';
import * as schema from './schema';
import { PatientsApi, DoctorsApi } from '../api';

// ACTION CREATORS

const makeRequestActionCreator = (type) => () => ({
	type
});

const fetchPatientsRequest = makeRequestActionCreator(actions.FETCH_PATIENTS_REQUEST);
const fetchDoctorsRequest = makeRequestActionCreator(actions.FETCH_DOCTORS_REQUEST);

const makeSuccessActionCreator = (type) => (payload) => ({
	type,
	payload
});

const fetchPatientsSuccess = makeSuccessActionCreator(actions.FETCH_PATIENTS_SUCCESS);
const fetchDoctorsSuccess = makeSuccessActionCreator(actions.FETCH_DOCTORS_SUCCESS);

const makeFailureActionCreator = (type) => (payload) => ({
	type,
	payload,
	error: true
});

const fetchPatientsFailure = makeFailureActionCreator(actions.FETCH_PATIENTS_FAILURE);
const fetchDoctorsFailure = makeFailureActionCreator(actions.FETCH_DOCTORS_FAILURE);

// ASYNC ACTION CREATORS

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
	dispatch(fetchPatientsRequest());

	return PatientsApi.fetchPatientById(id)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchPatientsSuccess(normalize([ response ], schema.patientsListSchema))),
			error => dispatch(fetchPatientsFailure(error))
		);
};

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

export const fetchDoctorById = (id) => (dispatch, getState) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctorById(id)
		.then(handleApiResponse)
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize([ response ], schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
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