import React from 'react';
import * as moment from 'moment';

import SchedulerCell from './SchedulerCell';

const SchedulerRow = ({ date, selectable, selectedDate, setSelectedDate, setDateTime, visits }) => {
	const cellDates = [];
	const currentDateTime = moment(date);
	for (let i = 0; i < 7; i++) {
		cellDates.push(moment(currentDateTime));
		currentDateTime.add(1, 'd');
	}

	return ( 
		<tr>
			<th className="hour">{date.format('HH:mm')}</th>
			{cellDates.map(date => (
				<SchedulerCell 
					key={date.get('date')} 
					date={date}
					selectable={selectable}
					selectedDate={selectedDate} 
					setSelectedDate={setSelectedDate} 
					setDateTime={setDateTime}
					visit={visits[date.format('YYYY-MM-DD HH:mm:ss')]}
				/>
			))}
		</tr>
	);
};

export default SchedulerRow;