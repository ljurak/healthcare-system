import React from 'react';

import PatientsListItem from './PatientsListItem';

const PatientsList = ({ patients }) => {
	return (
		<React.Fragment>
			<h3 className="patients-list-title">Search results</h3>
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