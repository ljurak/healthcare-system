import { schema } from 'normalizr';

export const patientSchema = new schema.Entity('patients');
export const patientsListSchema = new schema.Array(patientSchema);

export const doctorSchema = new schema.Entity('doctors');
export const doctorsListSchema = new schema.Array(doctorSchema);