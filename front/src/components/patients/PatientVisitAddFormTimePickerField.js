import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const PatientVisitAddFormTimePickerField = ({ id, name, value, onChange, onBlur }) => {

	const handleChange = (value) => {
		onChange(name, value);
	};

	const handleBlur = (e) => {
		onBlur(e);
	};

	const minTime = new Date();
	minTime.setHours(8);
	minTime.setMinutes(0);
	const maxTime = new Date();
	maxTime.setHours(19);
	maxTime.setMinutes(0);
	

	return (
		<DatePicker
			autocomplete="off"
			id={id}
			name={name}
			selected={value}
			onChange={handleChange}
			onBlur={handleBlur}
			showTimeSelect
			showTimeSelectOnly
			timeIntervals={60}
			minTime={minTime}
			maxTime={maxTime}
			dateFormat="HH:mm"
			timeFormat="HH:mm"
			placeholderText="HH:MM" />
	);	
};

export default PatientVisitAddFormTimePickerField;