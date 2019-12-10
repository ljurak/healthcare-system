import React from 'react';
import logo from '../logo.svg';
import { fetchPatientsByLastname } from '../actions';
import { connect } from 'react-redux';
import Header from './Header';
import { Switch, Route } from 'react-router-dom';
import PatientsPage from './PatientsPage';
import DoctorsPage from './DoctorsPage';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            searchInput: ''
        }
    }

    handleChange = e => {
        this.setState({
            searchInput: e.target.value
        });
    }

    handleSearchFormSubmit = e => {
        e.preventDefault();
        this.props.fetchPatientsByLastname(this.state.searchInput);
    }

    render() {
        return (
            <React.Fragment>
                <Header />
                <div className="main-content-wrapper">
                	<div className="container">
                		<aside className="sidebar"></aside>
                		<main className="main-content">
                			<Switch>
                				<Route path="/patients" component={PatientsPage} />
                				<Route path="/doctors" component={DoctorsPage} />
		                    
			                    <div>
			                        <ul>
			                            {this.props.patients.map(patient => <li key={patient.id}>{`${patient.firstName} ${patient.lastName}`}</li>)}
			                        </ul>
			                    </div>
		                    </Switch>
                		</main>	                	
	                </div>
                </div>
                <footer>
                    <ul>
                        <li><a>Home Page</a></li>
                        <li><a>Patients</a></li>
                        <li><a>Doctors</a></li>
                        <li><a>Info</a></li>
                    </ul>
                </footer>
            </React.Fragment>
        );
    }    
}

const mapStateToProps = state => {
    return {
        patients: state.entities.patients.visibleIds.map(id => state.entities.patients.byId[id])
    };
};

const mapDispatchToProps = { fetchPatientsByLastname };

export default connect(mapStateToProps, mapDispatchToProps)(App);