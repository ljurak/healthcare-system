import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const PatientVisitAddFormDatePickerField = ({ id, name, value, onChange, onBlur }) => {

	const handleChange = (value) => {
		onChange(name, value);
	};

	const handleBlur = (e) => {
		onBlur(e);
	};

	const minDate = new Date();
	const maxDate = new Date();
	maxDate.setMonth(maxDate.getMonth() + 6);

	return (
		<DatePicker
			autocomplete="off"
			id={id}
			name={name}
			selected={value}
			onChange={handleChange}
			onBlur={handleBlur}
			minDate={minDate}
			maxDate={maxDate}
			dateFormat="yyyy-MM-dd"
			placeholderText="YYYY-MM-DD" />
	);	
};

export default PatientVisitAddFormDatePickerField;