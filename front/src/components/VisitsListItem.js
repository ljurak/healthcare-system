import React from 'react';
import '../fontello/css/fontello.css';

import VisitEditForm from './VisitEditForm';

class VisitsListItem extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			showEditForm: false
		};
	}

	handleClick = e => {
		this.setState(prevState => {
			return { showEditForm: !prevState.showEditForm };
		});
	}

	render() {
		const { visit, renderForPatient } = this.props;
		const showEditForm = this.state.showEditForm;
		return (
			<div className={'visits-list-item' + (showEditForm ? ' opened' : '')}>
				<div className="visits-list-item-date">
					Date: <i>{visit.visitDate + ' ' + visit.visitTime.substr(0,5)}</i>
				</div>
				<div className="visits-list-item-status">
					Status: {visit.status}
				</div>
				<div className={renderForPatient ? 'visits-list-item-doctor' : 'visits-list-item-patient'}>
					{ renderForPatient
						? (<span>Doctor: <i>{visit.doctorName}</i></span>)
						: (<span>Patient: <i>{visit.patientName}</i></span>)
					}
				</div>
				{showEditForm && <VisitEditForm visit={visit} />}
				<button className="show-info-btn" type="button" onClick={this.handleClick}><i className="icon-down-open"></i></button>
			</div>
		);
	}
}

export default VisitsListItem;