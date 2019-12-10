const BASE_API_URL = 'http://localhost:8080';

const PatientsApi = {};

PatientsApi.fetchPatients = function() {
	return fetch(`${BASE_API_URL}/patients`);
};

PatientsApi.fetchPatientsByLastname = function(lastname) {
	return fetch(`${BASE_API_URL}/patients?lastname=${lastname}`);
};

PatientsApi.fetchPatientById = function(id) {
	return fetch(`${BASE_API_URL}/patients/${id}`);
};

PatientsApi.registerPatient = function(patient) {
	return fetch(`${BASE_API_URL}/patients`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(patient)
	});
};

PatientsApi.updatePatient = function(patient, id) {
	return fetch(`${BASE_API_URL}/patients/${id}`, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(patient)
	});
};

const DoctorsApi = {};

DoctorsApi.fetchDoctors = function() {
	return fetch(`${BASE_API_URL}/doctors`);
};

DoctorsApi.fetchDoctorsByLastname = function(lastname) {
	return fetch(`${BASE_API_URL}/doctors?lastname=${lastname}`);
};

DoctorsApi.fetchDoctorById = function(id) {
	return fetch(`${BASE_API_URL}/doctors/${id}`);
};

export { PatientsApi, DoctorsApi };