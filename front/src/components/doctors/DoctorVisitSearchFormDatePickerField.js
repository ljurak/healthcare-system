import React from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const DoctorVisitSearchFormDatePickerField = ({ id, name, value, onChange, onBlur }) => {

	const handleChange = (value) => {
		onChange(name, value);
	};

	const handleBlur = (e) => {
		onBlur(e);
	};

	return (
		<DatePicker
			autoComplete="off"
			id={id}
			name={name}
			selected={value}
			onChange={handleChange}
			onBlur={handleBlur}
			dateFormat="yyyy-MM-dd"
			placeholderText="YYYY-MM-DD" 
		/>
	);	
};

export default DoctorVisitSearchFormDatePickerField;