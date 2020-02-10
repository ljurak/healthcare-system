import React from 'react';
import * as moment from 'moment';

class SchedulerContainer extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			startDate: moment().startOf('week'),
			startTime: '08:00',
			endTime: '19:00',
			markedDate: null
		};
	}
 
	setPreviousWeek = () => {
		this.setState(prevState => ({ 
			startDate: moment(prevState.startDate).subtract(1, 'w')
		}));
	}

	setNextWeek = () => {
		this.setState(prevState => ({ 
			startDate: moment(prevState.startDate).add(1, 'w')
		}));
	}

	setMarkedDate = (date) => {
		this.setState({ markedDate: date });
	}
	
	render() {
		const { startDate, startTime, endTime, markedDate } = this.state;

		return (
			<div>
				<SchedulerHeader 
					startDate={startDate} 
					setPreviousWeek={this.setPreviousWeek} 
					setNextWeek={this.setNextWeek} 
				/>
				<Scheduler
					setDateTime={this.props.setDateTime}
					startDate={startDate} 
					startTime={startTime} 
					endTime={endTime}
					markedDate={markedDate}
					setMarkedDate={this.setMarkedDate}
				/>
			</div>
		);
	}
};

const SchedulerHeader = ({ startDate, setPreviousWeek, setNextWeek }) => {
	const endDate = moment(startDate).add(6, 'd');
	return (
		<div className="visit-calendar-header">
			<button className="btn prev-btn" type="button" onClick={setPreviousWeek}>Previous</button>
			<div className="date-range">{startDate.format('DD/MM/YYYY') + ' - ' + endDate.format('DD/MM/YYYY')}</div>
			<button className="btn next-btn" type="button" onClick={setNextWeek}>Next</button>
		</div>
	);
};

const Scheduler = ({ startDate, startTime, endTime, setDateTime, markedDate, setMarkedDate }) => {
	
	const days = [];
	const currentDate = moment(startDate);
	for (let i = 0; i < 7; i++) {
		days.push(moment(currentDate));
		currentDate.add(1, 'd');
	}

	const tableRows = [];
	const daysStrings = days.map(day => day.format('DD-MM-YYYY'));
	const currentHour = moment(startTime, 'HH:mm');
	const endHour = moment(endTime, 'HH:mm');
	while (currentHour.isSameOrBefore(endHour)) {
		const hour = currentHour.format('HH:mm');
		const row = <SchedulerRow key={hour} days={daysStrings} time={hour} setDateTime={setDateTime} markedDate={markedDate} setMarkedDate={setMarkedDate} />;
		tableRows.push(row);
		currentHour.add(1, 'h');
	}

	return (
		<table className="visit-calendar">
			<thead>
				<tr>
					<th className="date"></th>
					{days.map(day => (
						<th className="date" key={day}>
							<div className="day-of-week">{day.format('ddd')}</div>
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

const SchedulerRow = ({ days, time, setDateTime, markedDate, setMarkedDate }) => (
	<tr>
		<th className="time">{time}</th>
		{days.map(day => (
			<SchedulerCell key={day} date={day} time={time} setDateTime={setDateTime} markedDate={markedDate} setMarkedDate={setMarkedDate} />
		))}
	</tr>
);

const SchedulerCell = ({ date, time, setDateTime, markedDate, setMarkedDate }) => {
	const setFormValues = () => {
		setDateTime(moment(date, 'DD-MM-YYYY').toDate(), moment(time, 'HH:mm').toDate());
		setMarkedDate(moment(`${date} ${time}`, 'DD-MM-YYYY HH:mm'));
	};
	const marked = moment(`${date} ${time}`, 'DD-MM-YYYY HH:mm').isSame(markedDate);

	return (
		<td className={'cell' + (marked ? ' marked' : '')} onClick={setFormValues}></td>
	);
};

export default SchedulerContainer;