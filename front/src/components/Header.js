import React from 'react';
import { Link, NavLink } from 'react-router-dom'; 

const Header = () => {
	return (
		<header className="page-header">
			<div className="container">	
				<h1 className="page-header-logo">
					<Link to="/"><img src="./logo.png" /></Link>
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
			            	<NavLink to="/info">Info</NavLink>
			            </li>
			        </ul>
	            </nav>
            </div>
		</header>
	);
};

export default Header;