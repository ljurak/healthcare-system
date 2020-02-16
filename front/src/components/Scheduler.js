import React from 'react';
import { connect } from 'react-redux';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import * as moment from 'moment';

import { fetchVisitsForScheduler } from '../actions';
import { getVisitsForScheduler } from '../selectors';

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

const SchedulerCalendar = ({ selectable, startDate, startDayHour, endDayHour, selectedDate, setSelectedDate, setDateTime, visits }) => {
	
	const days = [];
	const currentDate = moment(startDate);
	for (let i = 0; i < 7; i++) {
		days.push(moment(currentDate));
		currentDate.add(1, 'd');
	}

	const tableRows = [];
	const currentDateTime = moment(startDate).set('hour', startDayHour);
	let currentHour = startDayHour;
	while (currentHour <= endDayHour) {
		const startDateForRow = moment(currentDateTime);
		const row = <SchedulerRow
			key={currentHour}
			date={startDateForRow}
			selectable={selectable}
			selectedDate={selectedDate} 
			setSelectedDate={setSelectedDate}
			setDateTime={setDateTime}
			visits={visits} 
		/>;
		tableRows.push(row);
		currentDateTime.add(1, 'h');
		currentHour++;
	}

	return (
		<table className="visit-calendar">
			<thead>
				<tr>
					<th className="weekday"></th>
					{days.map(day => (
						<th className="weekday" key={day}>
							<div>{day.format('ddd')}</div>
							<div>{day.format('DD/MM')}</div>
						</th>
					))}
				</tr>
			</thead>
			<tbody>
				{tableRows}
			</tbody>
		</table>
	);
};

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

class SchedulerCell extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			showDetails: false
		};
	}

	handleMouseOver = () => {
		this.setState({ showDetails: true });
	}

	handleMouseOut = () => {
		this.setState({ showDetails: false });
	}

	handleClick = () => {
		const { date, selectable, setDateTime, setSelectedDate } = this.props;
		if (selectable) {
			setDateTime(date.toDate());
			setSelectedDate(date);
		}
	}

	render() {
		const { showDetails } = this.state;
		const { date, selectedDate, visit } = this.props;
		const isMarked = date.isSame(selectedDate);
		const isActive = date.isAfter(moment());

		return !isActive
			? (<td className="cell inactive"></td>)
			: visit
				? (
					<td className="cell unavailable" onMouseOver={this.handleMouseOver} onMouseOut={this.handleMouseOut}>
						{showDetails &&
							<div className="visit-info">
								<p>Patient: {visit.patientName}</p>
								<p>Date: {visit.visitDate}</p>
								<p>Time: {visit.visitTime}</p>
							</div>
						}
					</td>
				) : (
					<td className={'cell active' + (isMarked ? ' marked' : '')} onClick={this.handleClick}></td>
				);
	}
}

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