import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

import * as moment from 'moment';

const SchedulerDatePicker = ({ startDate, minDate, maxDate, setWeek }) => {

	const handleChange = (date) => {
		if (moment(date).isValid()) {
			setWeek(moment(date).startOf('week'));
		}
	};

	return (
		<DatePicker
			autoComplete="off"
			className="date-range"
			selected={startDate.toDate()}
			onChange={handleChange}
			minDate={minDate.toDate()}
			maxDate={maxDate.toDate()}
			dateFormat="dd-MM-yyyy"
			placeholderText="DD-MM-YYYY" 
		/>
	);
};

export default SchedulerDatePicker;