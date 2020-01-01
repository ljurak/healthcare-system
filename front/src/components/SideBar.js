import React from 'react';

const SideBar = () => (
	<aside className="sidebar">
		<figure>
			<img className="photo" src="/healthcare.jpg" alt="" />
		</figure>
		<h3 className="system-info">System functionality</h3>
		<div className="group-info-title">Patients</div>
		<ul className="group-info-list">
			<li>Registering new patients</li>
			<li>Managing patients data</li>
			<li>Adding new visits</li>
			<li>Tracking treatment history</li>
		</ul>
		<div className="group-info-title">Doctors</div>
		<ul className="group-info-list">
			<li>Registering new doctors</li>
			<li>Managing doctors data</li>
			<li>Browsing visits</li>
		</ul>
	</aside>
);

export default SideBar;