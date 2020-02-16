import React from 'react';
import { connect } from 'react-redux';
import * as moment from 'moment';

import SchedulerHeader from './SchedulerHeader';
import SchedulerCalendar from './SchedulerCalendar';
import { fetchVisitsForScheduler } from '../../actions';
import { getVisitsForScheduler } from '../../selectors';

class Scheduler extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			currentDate: moment(),
			startDate: moment().startOf('week'),
			minDate: moment(),
			maxDate: moment().add(6, 'M'),
			startDayHour: 8,
			endDayHour: 19,
			selectedDate: null
		};
	}

	componentDidMount() {
		if (this.props.doctorId) {
			this.fetchVisits(this.state.startDate);
		}
	}

	componentDidUpdate(prevProps) {
		if (this.props.doctorId !== prevProps.doctorId) {
			this.fetchVisits(this.state.startDate);
			this.setSelectedDate(null);
		}
	}

	setWeek = (date) => {
		this.setState({ startDate: date });
		this.fetchVisits(date);
	}

	fetchVisits = (date) => {
		if (this.props.doctorId) {
			const doctorId = parseInt(this.props.doctorId, 10);
			const startDate = date.format('YYYY-MM-DD');
			const endDate = moment(date).add(6, 'd').format('YYYY-MM-DD');
			this.props.fetchVisits(doctorId, startDate, endDate);
		}	
	}

	setSelectedDate = (date) => {
		this.setState({ selectedDate: date });
	}
	
	render() {
		const { startDate, minDate, maxDate, startDayHour, endDayHour, selectedDate } = this.state;
		const { selectable, setDateTime, visits } = this.props;

		return (
			<div className="visit-scheduler">
				<SchedulerHeader 
					startDate={startDate}
					minDate={minDate}
					maxDate={maxDate}
					setWeek={this.setWeek} 
				/>
				<SchedulerCalendar
					selectable={selectable}
					setDateTime={setDateTime}
					startDate={startDate} 
					startDayHour={startDayHour} 
					endDayHour={endDayHour}
					selectedDate={selectedDate}
					setSelectedDate={this.setSelectedDate}
					visits={visits}
				/>
			</div>
		);
	}
};

const mapStateToProps = state => {
	return {
		visits: getVisitsForScheduler(state)
	};
};

const mapDispatchToProps = { 
	fetchVisits: fetchVisitsForScheduler
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(Scheduler);