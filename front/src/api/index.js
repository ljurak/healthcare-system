const BASE_API_URL = 'http://localhost:8080';

const createAuthorizationHeader = () => {
	const token = localStorage.getItem('token');
	if (token) {
		return { 'Authorization': `Bearer ${token}` };
	}
	return {};
};

const PatientsApi = {};

PatientsApi.fetchPatients = function() {
	return fetch(`${BASE_API_URL}/patients`, {
		headers: createAuthorizationHeader()
	});
};

PatientsApi.fetchPatientsByLastname = function(lastname) {
	return fetch(`${BASE_API_URL}/patients?lastname=${lastname}`, {
		headers: createAuthorizationHeader()
	});
};

PatientsApi.fetchPatientById = function(id) {
	return fetch(`${BASE_API_URL}/patients/${id}`, {
		headers: createAuthorizationHeader()
	});
};

PatientsApi.addPatient = function(patient) {
	return fetch(`${BASE_API_URL}/patients`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(patient)
	});
};

PatientsApi.updatePatient = function(patient, id) {
	return fetch(`${BASE_API_URL}/patients/${id}`, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(patient)
	});
};

const DoctorsApi = {};

DoctorsApi.fetchDoctors = function() {
	return fetch(`${BASE_API_URL}/doctors`, {
		headers: createAuthorizationHeader()
	});
};

DoctorsApi.fetchDoctorsByLastname = function(lastname) {
	return fetch(`${BASE_API_URL}/doctors?lastname=${lastname}`, {
		headers: createAuthorizationHeader()
	});
};

DoctorsApi.fetchDoctorById = function(id) {
	return fetch(`${BASE_API_URL}/doctors/${id}`, {
		headers: createAuthorizationHeader()
	});
};

const LoginApi = {};

LoginApi.login = function(user) {
	return fetch(`${BASE_API_URL}/login`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(user)
	});
}

export { PatientsApi, DoctorsApi, LoginApi };