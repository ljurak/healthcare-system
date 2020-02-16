import React from 'react';
import * as moment from 'moment';

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
			if (typeof setDateTime === 'function') {
				setDateTime(date.toDate());
			}			
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

export default SchedulerCell;