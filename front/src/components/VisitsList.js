import React from 'react';

import VisitsListItem from './VisitsListItem';

const VisitsList = ({ visits, renderForPatient }) => {
	return (
		<div className="visits-list">
			{visits.map(visit => <VisitsListItem key={visit.id} visit={visit} renderForPatient={renderForPatient} />)}
		</div>
	);
};

export default VisitsList;