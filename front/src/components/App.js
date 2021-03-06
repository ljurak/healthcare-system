import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import Header from './Header';
import SideBar from './SideBar';
import Footer from './Footer';
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
                            <PrivateRoute path="/patients" component={PatientComponent} />
                            <PrivateRoute path="/doctors" component={DoctorComponent} />
                            <Route path="/login" component={LoginPage} />
                            <Redirect from="*" to="/patients" />
                        </Switch>
            		</main>	                	
                </div>
            </div>
            <Footer />
        </React.Fragment>
    );
}

export default App;