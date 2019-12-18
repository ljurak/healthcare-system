import React from 'react';
import { Link } from 'react-router-dom';

const PeopleListItem = ({ person, baseUrl }) => {
	return (
		<tr>
			<td>
				<Link className="person-link" to={`${baseUrl}/${person.id}`}>{`${person.firstName} ${person.lastName}`}</Link>
			</td>
			<td>{person.birthDate}</td>
			<td>{person.address}</td>
			<td>{person.phoneNumber}</td>
		</tr>
	);
};

export default PeopleListItem;