import React from 'react';
import { Link, NavLink } from 'react-router-dom'; 
import { connect } from 'react-redux';

const Header = ({ isLoggedIn }) => {
	return (
		<header className="page-header">
			<div className="container">	
				<h1 className="page-header-logo">
					<Link to="/"><img src="./logo.png" alt="Healthcare system logo" /></Link>
				</h1>
				<nav className="page-nav">
		            <ul className="page-nav-list">
			            <li className="page-nav-list-item">
			            	<NavLink exact to="/">Home</NavLink>
			            </li>
			            <li className="page-nav-list-item">
			            	<NavLink to="/patients">Patients</NavLink>
			            </li>
			            <li className="page-nav-list-item">
			            	<NavLink to="/doctors">Doctors</NavLink>
			            </li>
			            <li className="page-nav-list-item">
			            	{ isLoggedIn 
			            		? <NavLink to="/login" className="login-btn">Logout</NavLink>
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

export default connect(
	mapStateToProps
)(Header);