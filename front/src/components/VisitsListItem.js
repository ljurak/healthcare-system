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
		const visit = this.props.visit;
		const showEditForm = this.state.showEditForm;
		return (
			<div className={'visits-list-item' + (showEditForm ? ' opened' : '')}>
				<div className="visits-list-item-date">
					Date: <i>{visit.visitDate + ' ' + visit.visitTime.substr(0,5)}</i>
				</div>
				<div className="visits-list-item-doctor">
					Doctor: <i>{visit.doctorName}</i>
				</div>
				{showEditForm && <VisitEditForm visit={visit} />}
				<button className="show-info-btn" type="button" onClick={this.handleClick}><i className="icon-down-open"></i></button>
			</div>
		);
	}
}

export default VisitsListItem;