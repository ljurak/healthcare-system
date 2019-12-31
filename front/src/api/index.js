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

DoctorsApi.fetchDoctorsBySpecialty = function(specialty) {
	return fetch(`${BASE_API_URL}/doctors?specialty=${specialty}`, {
		headers: createAuthorizationHeader()
	});
};

DoctorsApi.fetchDoctorById = function(id) {
	return fetch(`${BASE_API_URL}/doctors/${id}`, {
		headers: createAuthorizationHeader()
	});
};

DoctorsApi.addDoctor = function(doctor) {
	return fetch(`${BASE_API_URL}/doctors`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(doctor)
	});
};

DoctorsApi.updateDoctor = function(doctor, id) {
	return fetch(`${BASE_API_URL}/doctors/${id}`, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(doctor)
	});
};

const VisitsApi = {};

VisitsApi.fetchVisitsByPatient = function(patientId) {
	return fetch(`${BASE_API_URL}/patients/${patientId}/visits`, {
		headers: createAuthorizationHeader()
	});
};

VisitsApi.fetchVisitsByDoctor = function(doctorId) {
	return fetch(`${BASE_API_URL}/doctors/${doctorId}/visits`, {
		headers: createAuthorizationHeader()
	});
};

VisitsApi.addVisit = function(visit, patientId) {
	return fetch(`${BASE_API_URL}/patients/${patientId}/visits`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(visit)
	});
};

VisitsApi.updateVisit = function(visit, patientId) {
	return fetch(`${BASE_API_URL}/patients/${patientId}/visits`, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json', ...createAuthorizationHeader() },
		body: JSON.stringify(visit)
	});
};

const SpecialtiesApi = {};

SpecialtiesApi.fetchSpecialties = function() {
	return fetch(`${BASE_API_URL}/specialties`, {
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

export { PatientsApi, DoctorsApi, VisitsApi, SpecialtiesApi, LoginApi };