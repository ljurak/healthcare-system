import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const PatientVisitAddFormTimePickerField = ({ field, form, ...props }) => {

	const { name, value } = field;
	const { onChange } = props;

	const handleChange = (date) => onChange(name, date);

	const minTime = new Date();
	minTime.setHours(8);
	minTime.setMinutes(0);
	const maxTime = new Date();
	maxTime.setHours(19);
	maxTime.setMinutes(0);

	return (
		<DatePicker
			{...field}
			{...props}
			selected={value}
			onChange={handleChange}
			showTimeSelect
			showTimeSelectOnly
			timeIntervals={60}
			minTime={minTime}
			maxTime={maxTime}
			autoComplete="off"
			dateFormat="HH:mm"
			timeFormat="HH:mm"
			placeholderText="HH:MM" 
		/>
	);	
};

export default PatientVisitAddFormTimePickerField;