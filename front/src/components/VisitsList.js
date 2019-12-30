import React from 'react';

import VisitsListItem from './VisitsListItem';

const VisitsList = ({ visits }) => {
	return (
		<div className="visits-list">
			{visits.map(visit => <VisitsListItem key={visit.id} visit={visit} />)}
		</div>
	);
};

export default VisitsList;