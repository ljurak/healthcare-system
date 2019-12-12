import React from 'react';

import PatientsListItem from './PatientsListItem';

const PatientsList = ({ patients, header }) => {
	return (
		<React.Fragment>
			<h3 className="patients-list-title">{header}</h3>
			<table className="patients-list">
				<thead className="patients-list-header">
					<tr>
						<th>Full name</th>
						<th>Birth date</th>
						<th>Address</th>
						<th>Phone number</th>
					</tr>
				</thead>
				<tbody>
					{patients.map(patient => <PatientsListItem key={patient.id} patient={patient} />)}
				</tbody>
			</table>
		</React.Fragment>
	);
};

export default PatientsList;