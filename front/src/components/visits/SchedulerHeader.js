import React from 'react';
import * as moment from 'moment';

import SchedulerDatePicker from './SchedulerDatePicker';

const SchedulerHeader = ({ startDate, minDate, maxDate, setWeek }) => {
	const previousWeek = moment(startDate).subtract(7, 'd');
	const nextWeek = moment(startDate).add(7, 'd');
	const canGoPrevious = startDate.isAfter(minDate);
	const canGoNext = startDate.isBefore(maxDate);

	return (
		<div className="visit-calendar-header">
			<button 
				className="btn prev-btn" 
				type="button" 
				disabled={!canGoPrevious} 
				onClick={() => setWeek(previousWeek)}
			>
				Previous
			</button>
			<SchedulerDatePicker
				startDate={startDate}
				minDate={minDate}
				maxDate={maxDate}
				setWeek={setWeek} 
			/>
			<button 
				className="btn next-btn" 
				type="button" 
				disabled={!canGoNext} 
				onClick={() => setWeek(nextWeek)}
			>
				Next
			</button>
		</div>
	);
};

export default SchedulerHeader;