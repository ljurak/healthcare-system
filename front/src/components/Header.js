import React from 'react';
import { Link, NavLink, withRouter } from 'react-router-dom'; 
import { connect } from 'react-redux';

import { logout } from '../actions';

const Header = ({ isLoggedIn, logout, history }) => {
	const handleLogout = (e) => {
		e.preventDefault();
		logout();
		history.push('/login');
	};

	return (
		<header className="page-header">
			<div className="container">	
				<h1 className="page-header-logo">
					<Link to="/"><img src="/logo.png" alt="Healthcare system logo" /></Link>
				</h1>
				<nav className="page-nav">
		            <ul className="page-nav-list">
			            <li className="page-nav-list-item">
			            	<NavLink to="/patients">Patients</NavLink>
			            </li>
			            <li className="page-nav-list-item">
			            	<NavLink to="/doctors">Doctors</NavLink>
			            </li>
			            <li className="page-nav-list-item">
			            	{ isLoggedIn 
			            		? <NavLink to="/logout" className="login-btn" onClick={handleLogout}>Logout</NavLink>
			            		: <NavLink to="/login" className="login-btn">Login</NavLink>
			            	}
			            </li>
			        </ul>
	            </nav>
            </div>
		</header>
	);
};

const mapStateToProps = (state) => ({
	isLoggedIn: state.authentication.isLoggedIn
});

const mapDispatchToProps = { logout };

export default withRouter(connect(
	mapStateToProps,
	mapDispatchToProps
)(Header));