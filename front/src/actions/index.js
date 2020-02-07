import { normalize } from 'normalizr';

import * as actions from './actionTypes';
import * as schema from './schema';
import { PatientsApi, DoctorsApi, VisitsApi, SpecialtiesApi, LoginApi } from '../api';
import { getPatient, getDoctor } from '../reducers';
import { history } from '../helpers/history';

// ACTION CREATORS

const makeRequestActionCreator = (type) => () => ({	type });

const fetchPatientsRequest = makeRequestActionCreator(actions.FETCH_PATIENTS_REQUEST);
const fetchPatientRequest = makeRequestActionCreator(actions.FETCH_PATIENT_REQUEST);
const fetchDoctorsRequest = makeRequestActionCreator(actions.FETCH_DOCTORS_REQUEST);
const fetchDoctorRequest = makeRequestActionCreator(actions.FETCH_DOCTOR_REQUEST);
const fetchDoctorsBySpecialtyRequest = makeRequestActionCreator(actions.FETCH_DOCTORS_BY_SPECIALTY_REQUEST);
const fetchSpecialtiesRequest = makeRequestActionCreator(actions.FETCH_SPECIALTIES_REQUEST);
const fetchPatientVisitsRequest = makeRequestActionCreator(actions.FETCH_PATIENT_VISITS_REQUEST);
const fetchDoctorVisitsRequest = makeRequestActionCreator(actions.FETCH_DOCTOR_VISITS_REQUEST);
const addPatientRequest = makeRequestActionCreator(actions.ADD_PATIENT_REQUEST);
const updatePatientRequest = makeRequestActionCreator(actions.UPDATE_PATIENT_REQUEST);
const addDoctorRequest = makeRequestActionCreator(actions.ADD_DOCTOR_REQUEST);
const updateDoctorRequest = makeRequestActionCreator(actions.UPDATE_DOCTOR_REQUEST);
const addVisitRequest = makeRequestActionCreator(actions.ADD_VISIT_REQUEST);
const updateVisitRequest = makeRequestActionCreator(actions.UPDATE_VISIT_REQUEST);
const loginRequest = makeRequestActionCreator(actions.LOGIN_REQUEST);

const makeSuccessActionCreator = (type) => (payload) => ({ type, payload });

const fetchPatientsSuccess = makeSuccessActionCreator(actions.FETCH_PATIENTS_SUCCESS);
const fetchPatientSuccess = makeSuccessActionCreator(actions.FETCH_PATIENT_SUCCESS);
const fetchDoctorsSuccess = makeSuccessActionCreator(actions.FETCH_DOCTORS_SUCCESS);
const fetchDoctorSuccess = makeSuccessActionCreator(actions.FETCH_DOCTOR_SUCCESS);
const fetchDoctorsBySpecialtySuccess = makeSuccessActionCreator(actions.FETCH_DOCTORS_BY_SPECIALTY_SUCCESS);
const fetchSpecialtiesSuccess = makeSuccessActionCreator(actions.FETCH_SPECIALTIES_SUCCESS);
const fetchPatientVisitsSuccess = makeSuccessActionCreator(actions.FETCH_PATIENT_VISITS_SUCCESS);
const fetchDoctorVisitsSuccess = makeSuccessActionCreator(actions.FETCH_DOCTOR_VISITS_SUCCESS);
const addPatientSuccess = makeSuccessActionCreator(actions.ADD_PATIENT_SUCCESS);
const updatePatientSuccess = makeSuccessActionCreator(actions.UPDATE_PATIENT_SUCCESS);
const addDoctorSuccess = makeSuccessActionCreator(actions.ADD_DOCTOR_SUCCESS);
const updateDoctorSuccess = makeSuccessActionCreator(actions.UPDATE_DOCTOR_SUCCESS);
const addVisitSuccess = makeSuccessActionCreator(actions.ADD_VISIT_SUCCESS);
const updateVisitSuccess = makeSuccessActionCreator(actions.UPDATE_VISIT_SUCCESS);
const loginSuccess = makeSuccessActionCreator(actions.LOGIN_SUCCESS);

const makeFailureActionCreator = (type) => (payload) => ({ type, payload, error: true});

const fetchPatientsFailure = makeFailureActionCreator(actions.FETCH_PATIENTS_FAILURE);
const fetchPatientFailure = makeFailureActionCreator(actions.FETCH_PATIENT_FAILURE);
const fetchDoctorsFailure = makeFailureActionCreator(actions.FETCH_DOCTORS_FAILURE);
const fetchDoctorFailure = makeFailureActionCreator(actions.FETCH_DOCTOR_FAILURE);
const fetchDoctorsBySpecialtyFailure = makeFailureActionCreator(actions.FETCH_DOCTORS_BY_SPECIALTY_FAILURE);
const fetchSpecialtiesFailure = makeFailureActionCreator(actions.FETCH_SPECIALTIES_FAILURE);
const fetchPatientVisitsFailure = makeFailureActionCreator(actions.FETCH_PATIENT_VISITS_FAILURE);
const fetchDoctorVisitsFailure = makeFailureActionCreator(actions.FETCH_DOCTOR_VISITS_FAILURE);
const addPatientFailure = makeFailureActionCreator(actions.ADD_PATIENT_FAILURE);
const updatePatientFailure = makeFailureActionCreator(actions.UPDATE_PATIENT_FAILURE);
const addDoctorFailure = makeFailureActionCreator(actions.ADD_DOCTOR_FAILURE);
const updateDoctorFailure = makeFailureActionCreator(actions.UPDATE_DOCTOR_FAILURE);
const addVisitFailure = makeFailureActionCreator(actions.ADD_VISIT_FAILURE);
const updateVisitFailure = makeFailureActionCreator(actions.UPDATE_VISIT_FAILURE);
const loginFailure = makeFailureActionCreator(actions.LOGIN_FAILURE);

export const logout = () => {
	localStorage.removeItem('token');
	return { type: actions.LOGOUT };
};

export const clearAlert = () => {
	return { type: actions.CLEAR_ALERT };
};

// ASYNC ACTION CREATORS

// PATIENTS
export const fetchPatients = () => (dispatch) => {
	dispatch(fetchPatientsRequest());

	return PatientsApi.fetchPatients()
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchPatientsSuccess(normalize(response, schema.patientsListSchema))), 
			error => dispatch(fetchPatientsFailure(error))
		);
};

export const fetchPatientsByLastname = (lastname) => (dispatch) => {
	dispatch(fetchPatientsRequest());

	return PatientsApi.fetchPatientsByLastname(lastname)
		.then(response => handleApiResponse(response, dispatch))
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
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchPatientSuccess(normalize(response, schema.patientSchema))),
			error => dispatch(fetchPatientFailure(error))
		);
};

export const addPatient = (patient) => (dispatch) => {
	dispatch(addPatientRequest());

	return PatientsApi.addPatient(patient)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(addPatientSuccess(normalize(response, schema.patientSchema))),
			error => dispatch(addPatientFailure(error))
		);
};

export const updatePatient = (patient, id) => (dispatch) => {
	dispatch(updatePatientRequest());

	return PatientsApi.updatePatient(patient, id)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(updatePatientSuccess(normalize(response, schema.patientSchema))),
			error => dispatch(updatePatientFailure(error))
		);
};

// DOCTORS

export const fetchDoctors = () => (dispatch) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctors()
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
		);
};

export const fetchDoctorsByLastname = (lastname) => (dispatch) => {
	dispatch(fetchDoctorsRequest());

	return DoctorsApi.fetchDoctorsByLastname(lastname)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchDoctorsSuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsFailure(error))
		);
};

export const fetchDoctorsBySpecialty = (specialty) => (dispatch) => {
	dispatch(fetchDoctorsBySpecialtyRequest());

	return DoctorsApi.fetchDoctorsBySpecialty(specialty)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchDoctorsBySpecialtySuccess(normalize(response, schema.doctorsListSchema))),
			error => dispatch(fetchDoctorsBySpecialtyFailure(error))
		);
};

export const fetchDoctorById = (id) => (dispatch, getState) => {
	if (getDoctor(getState(), id)) {
		return Promise.resolve();
	}

	dispatch(fetchDoctorRequest());

	return DoctorsApi.fetchDoctorById(id)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchDoctorSuccess(normalize(response, schema.doctorSchema))),
			error => dispatch(fetchDoctorFailure(error))
		);
};

export const addDoctor = (doctor) => (dispatch) => {
	dispatch(addDoctorRequest());

	return DoctorsApi.addDoctor(doctor)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(addDoctorSuccess(normalize(response, schema.doctorSchema))),
			error => dispatch(addDoctorFailure(error))
		);
};

export const updateDoctor = (doctor, id) => (dispatch) => {
	dispatch(updateDoctorRequest());

	return DoctorsApi.updateDoctor(doctor, id)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(updateDoctorSuccess(normalize(response, schema.doctorSchema))),
			error => dispatch(updateDoctorFailure(error))
		);
};

// SPECIALTIES

export const fetchSpecialties = () => (dispatch, getState) => {
	if (getState().entities.specialties.visibleIds.length) {
		return Promise.resolve();
	}

	dispatch(fetchSpecialtiesRequest());

	return SpecialtiesApi.fetchSpecialties()
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchSpecialtiesSuccess(normalize(response, schema.specialtiesListSchema))),
			error => dispatch(fetchSpecialtiesFailure(error))
		);
};

// VISITS

export const fetchVisitsByPatientId = (patientId) => (dispatch) => {
	dispatch(fetchPatientVisitsRequest());

	return VisitsApi.fetchVisitsByPatient(patientId)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchPatientVisitsSuccess(normalize(response, schema.visitsListSchema))),
			error => dispatch(fetchPatientVisitsFailure(error))
		);
};

export const fetchVisitsByDoctorId = (doctorId, startDate, endDate) => (dispatch) => {
	dispatch(fetchDoctorVisitsRequest());

	return VisitsApi.fetchVisitsByDoctor(doctorId, startDate, endDate)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(fetchDoctorVisitsSuccess(normalize(response, schema.visitsListSchema))),
			error => dispatch(fetchDoctorVisitsFailure(error))
		);
};

export const addVisit = (visit, patientId) => (dispatch) => {
	dispatch(addVisitRequest());

	return VisitsApi.addVisit(visit, patientId)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(addVisitSuccess(normalize(response, schema.visitSchema))),
			error => dispatch(addVisitFailure(error))
		);
};

export const updateVisit = (visit, patientId) => (dispatch) => {
	dispatch(updateVisitRequest());

	return VisitsApi.updateVisit(visit, patientId)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => dispatch(updateVisitSuccess(normalize(response, schema.visitSchema))),
			error => dispatch(updateVisitFailure({ ...error, visitId: visit.id }))
		);
};

// LOGIN

export const login = (user) => (dispatch) => {
	dispatch(loginRequest());

	return LoginApi.login(user)
		.then(response => handleApiResponse(response, dispatch))
		.then(
			response => {
				localStorage.setItem('token', response.token);
				dispatch(loginSuccess(response));
			}, 
			error => dispatch(loginFailure(error))
		);
};

// helper function processing response from api
const handleApiResponse = (response, dispatch) => {
	return response.json().then(json => {
		if (response.status === 401) {
			dispatch(logout());
			history.push('/login');
		}
		if (!response.ok) {
			return Promise.reject(json);
		}

		return json;
	});
};
