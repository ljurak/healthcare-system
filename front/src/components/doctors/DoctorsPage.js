import React from 'react';
import { connect } from 'react-redux';

import DoctorAddForm from './DoctorAddForm';
import DoctorSearchForm from './DoctorSearchForm';
import PeopleList from '../PeopleList';
import { getVisibleDoctors, getVisibleSpecialties, getIsFetchingDoctors, getIsAddingDoctor } from '../../reducers';
import { addDoctor, fetchDoctorsByLastname, fetchSpecialties } from '../../actions';

class DoctorsPage extends React.Component {
	componentDidMount() {
		this.props.fetchSpecialties();
	}

	render() {
		const { doctors, specialties, isFetching, isAdding, fetchDoctorsByLastname, addDoctor } = this.props;
		return (
			<React.Fragment>
				<DoctorAddForm specialties={specialties} isAdding={isAdding} addDoctor={addDoctor} />
				<DoctorSearchForm isFetching={isFetching} fetchDoctors={fetchDoctorsByLastname} />
				{ doctors.length > 0
					? (<PeopleList people={doctors} baseUrl="/doctors" />)
					: (<div className="doctors-search-info">No results</div>) 
				}
			</React.Fragment>
		);
	}
}

const mapStateToProps = (state) => ({
	doctors: getVisibleDoctors(state),
	specialties: getVisibleSpecialties(state),
	isFetching: getIsFetchingDoctors(state),
	isAdding: getIsAddingDoctor(state)
});

const mapDispatchToProps = { 
	addDoctor, 
	fetchDoctorsByLastname,
	fetchSpecialties 
};

export default connect(
	mapStateToProps, 
	mapDispatchToProps
)(DoctorsPage);