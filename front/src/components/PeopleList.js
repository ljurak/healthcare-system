import React from 'react';

import PeopleListItem from './PeopleListItem';

const PeopleList = ({ people, baseUrl }) => {
	return (
		<table className="people-list">
			<thead className="people-list-header">
				<tr>
					<th>Full name</th>
					<th>Birth date</th>
					<th>Address</th>
					<th>Phone number</th>
				</tr>
			</thead>
			<tbody>
				{people.map(person => <PeopleListItem key={person.id} person={person} baseUrl={baseUrl} />)}
			</tbody>
		</table>
	);
};

export default PeopleList;