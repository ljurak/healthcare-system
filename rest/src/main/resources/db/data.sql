INSERT INTO patients (first_name, last_name, birth_date, address, phone_number, email, create_time, update_time) VALUES
	('Clementine', 'Bauch', '1981-05-21', '533 Monument Walk, Enetai, Illinois, 6436', '14631234447', 'nathan@yesenia.net', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Tami', 'Underwood', '1992-07-28', '732 Celeste Court, Lookingglass', '26731323437', 'elliscarr@senmei.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Chelsey', 'Dietrich', '1972-11-12', '730 Fenimore Street, Vaughn, Marshall Islands', '14466535554', 'denisedorsey@kidstock.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Dennis', 'Schulist', '1989-02-17', '499 Sackman Street, Websterville, California', '67641573436', 'myrtleryan@geekmosis.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Considine', 'Lockman', '1961-10-05', '633 Elm Avenue, Hartsville/Hartley, Rhode Island', '77651454665', 'avilarhodes@uberlux.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kurtis', 'Weissnat', '1992-04-02', '574 Boardwalk , Lutsen, New Hampshire', '34636755777', 'tamiunderwood@duflex.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Nicholas', 'Runolfsdottir', '1991-03-04', '270 Ruby Street, Corinne, Wisconsin, 6436', '66651564567', 'antoinettemason@vendblend.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Glenna', 'Reichert', '1974-12-16', '418 Prospect Avenue, Dawn, Arizona', '24738536756', 'rey.padberg@karina.biz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Clementina', 'DuBuque', '1975-01-11', '635 Kenmore Court, Manchester, Indiana', '84731276454', 'sherwood@rosamond.me', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Ellis', 'Carr', '1984-06-25', '875 Harrison Avenue, Valle, Wisconsin', '16538766866', 'telly.hoeger@billy.biz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO specialties (name) VALUES
	('allergy and immunology'),
	('dermatology'),
	('family medicine'),
	('gynecology'),
	('internal medicine'),
	('neurology'),
	('pediatrics'),
	('psychiatry'),
	('surgery'),
	('urology');
	
INSERT INTO doctors (first_name, last_name, birth_date, address, phone_number, email, specialty_id, create_time, update_time) VALUES
	('Coltrane', 'Wood', '1982-06-15', '43 Walker Street, Baltimore', '54651434744', 'wood@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Malrick', 'Modeny', '1976-08-25', '43 Walker Street, Baltimore', '15764234744', 'wood@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Katy', 'Pank', '1991-10-13', '43 Walker Street, Baltimore', '86578646754', 'wood@geek.net', 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Wendy', 'Vinson', '1986-07-15', '43 Walker Street, Baltimore', '42366738768', 'wood@geek.net', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Biern', 'Nardik', '1981-02-19', '43 Walker Street, Baltimore', '27643253434', 'wood@geek.net', 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lyoto', 'Nuka', '1972-12-03', '43 Walker Street, Baltimore', '64453428585', 'wood@geek.net', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Sven', 'Weis', '1977-01-20', '43 Walker Street, Baltimore', '43456335457', 'wood@geek.net', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Gerhard', 'Schot', '1980-09-04', '43 Walker Street, Baltimore', '83451833577', 'wood@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kendy', 'Moody', '1979-07-09', '43 Walker Street, Baltimore', '25437458732', 'wood@geek.net', 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lares', 'Arpag', '1983-05-02', '43 Walker Street, Baltimore', '72465647845', 'wood@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO roles (name) VALUES ('admin');

INSERT INTO users (username, password, first_name, last_name, create_time, update_time) VALUES
	('admin', '{bcrypt}$2b$10$e/b1Zz12zfjOBM9JfudgueTVvO7PhjBcQyUiyNbHcge0ifKM9Ht6u', 'Kiorn', 'Winuto', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
	
INSERT INTO users_roles (user_id, role_id) VALUES
	(1, 1);