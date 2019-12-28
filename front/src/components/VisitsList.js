import React from 'react';

import VisitsListItem from './VisitsListItem';

const VisitsList = ({ visits }) => {
	return (
		<table className="visits-list">
			<thead className="visits-list-header">
				<tr>
					<th>Patient</th>
					<th>Doctor</th>
					<th>Visit date</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				{visits.map(visit => <VisitsListItem key={visit.id} visit={visit} />)}
			</tbody>
		</table>
	);
};

export default VisitsList;