import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { connect } from 'react-redux';

import Header from './Header';
import Footer from './Footer';
import PatientComponent from './PatientComponent';
import DoctorsPage from './DoctorsPage';

const App = () => (
    <React.Fragment>
        <Header />
        <div className="main-content-wrapper">
        	<div className="container">
        		<aside className="sidebar"></aside>
        		<main className="main-content">
        			<Switch>
        				<Route path="/patients" component={PatientComponent} />
        				<Route path="/doctors" component={DoctorsPage} />
                    </Switch>
        		</main>	                	
            </div>
        </div>
        <Footer />
    </React.Fragment>
);

export default App;