import React from 'react';

const VisitsListItem = ({ visit }) => (
	<tr>
		<td>{visit.patientId}</td>
		<td>{visit.doctorId}</td>
		<td>{visit.visitDate + ' ' + visit.visitTime}</td>
		<td>{visit.status}</td>
	</tr>
);

export default VisitsListItem;