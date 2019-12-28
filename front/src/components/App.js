import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import Header from './Header';
import SideBar from './SideBar';
import Footer from './Footer';
import MainContent from './MainContent';
import PatientComponent from './patients/PatientComponent';
import DoctorComponent from './doctors/DoctorComponent';
import LoginPage from './LoginPage';
import PrivateRoute from './PrivateRoute';

const App = () => {
    return (
        <React.Fragment>
            <Header />
            <div className="main-content-wrapper">
            	<div className="container">
            		<SideBar />
            		<main className="main-content">
            			<Switch>
                            <PrivateRoute exact path="/" component={MainContent} />
                            <PrivateRoute path="/patients" component={PatientComponent} />
                            <PrivateRoute path="/doctors" component={DoctorComponent} />
                            <Route path="/login" component={LoginPage} />
                            <Redirect from="*" to="/" />
                        </Switch>
            		</main>	                	
                </div>
            </div>
            <Footer />
        </React.Fragment>
    );
}

export default App;