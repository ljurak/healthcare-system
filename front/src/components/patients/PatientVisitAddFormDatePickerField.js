import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const PatientVisitAddFormDatePickerField = ({ field, form, ...props }) => {

	const { name, value } = field;
	const { onChange } = props;

	const handleChange = (date) => onChange(name, date);

	const minDate = new Date();
	const maxDate = new Date();
	maxDate.setMonth(maxDate.getMonth() + 6);

	return (
		<DatePicker
			{...field}
			{...props}
			selected={value}
			onChange={handleChange}
			minDate={minDate}
			maxDate={maxDate}
			autoComplete="off"
			dateFormat="yyyy-MM-dd"
			placeholderText="YYYY-MM-DD" 
		/>
	);
};

export default PatientVisitAddFormDatePickerField;