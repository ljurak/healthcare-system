import { schema } from 'normalizr';

export const patientSchema = new schema.Entity('patients');
export const patientsListSchema = new schema.Array(patientSchema);

export const doctorSchema = new schema.Entity('doctors');
export const doctorsListSchema = new schema.Array(doctorSchema);

export const specialtySchema = new schema.Entity('specialties');
export const specialtiesListSchema = new schema.Array(specialtySchema);

export const visitSchema = new schema.Entity('visits');
export const visitsListSchema = new schema.Array(visitSchema);