import React from 'react';
import * as moment from 'moment';

import SchedulerRow from './SchedulerRow';

const SchedulerCalendar = ({ selectable, startDate, startDayHour, endDayHour, selectedDate, setSelectedDate, setDateTime, visits }) => {
	
	const days = [];
	const currentDate = moment(startDate);
	for (let i = 0; i < 7; i++) {
		days.push(moment(currentDate));
		currentDate.add(1, 'd');
	}

	const tableRows = [];
	const currentDateTime = moment(startDate).set('hour', startDayHour);
	let currentHour = startDayHour;
	while (currentHour <= endDayHour) {
		const startDateForRow = moment(currentDateTime);
		const row = <SchedulerRow
			key={currentHour}
			date={startDateForRow}
			selectable={selectable}
			selectedDate={selectedDate} 
			setSelectedDate={setSelectedDate}
			setDateTime={setDateTime}
			visits={visits} 
		/>;
		tableRows.push(row);
		currentDateTime.add(1, 'h');
		currentHour++;
	}

	return (
		<table className="visit-calendar">
			<thead>
				<tr>
					<th className="weekday"></th>
					{days.map(day => (
						<th className="weekday" key={day}>
							<div>{day.format('ddd')}</div>
							<div>{day.format('DD/MM')}</div>
						</th>
					))}
				</tr>
			</thead>
			<tbody>
				{tableRows}
			</tbody>
		</table>
	);
};

export default SchedulerCalendar;