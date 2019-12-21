import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import Header from './Header';
import Footer from './Footer';
import MainContent from './MainContent';
import PatientComponent from './patients/PatientComponent';
import DoctorsPage from './DoctorsPage';
import LoginPage from './LoginPage';
import PrivateRoute from './PrivateRoute';

const App = () => (
    <React.Fragment>
        <Header />
        <div className="main-content-wrapper">
        	<div className="container">
        		<aside className="sidebar"></aside>
        		<main className="main-content">
        			<Switch>
                        <PrivateRoute exact path="/" component={MainContent} />
                        <PrivateRoute path="/patients" component={PatientComponent} />
                        <PrivateRoute path="/doctors" component={DoctorsPage} />
                        <Route path="/login" component={LoginPage} />
                        <Redirect from="*" to="/" />
                    </Switch>
        		</main>	                	
            </div>
        </div>
        <Footer />
    </React.Fragment>
);

export default App;