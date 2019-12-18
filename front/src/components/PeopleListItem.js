import React from 'react';
import { Link } from 'react-router-dom';

const PatientsListItem = ({ patient }) => {
	return (
		<tr>
			<td>
				<Link className="patient-link" to={`/patients/${patient.id}`}>{`${patient.firstName} ${patient.lastName}`}</Link>
			</td>
			<td>{patient.birthDate}</td>
			<td>{patient.address}</td>
			<td>{patient.phoneNumber}</td>
		</tr>
	);
};

export default PatientsListItem;