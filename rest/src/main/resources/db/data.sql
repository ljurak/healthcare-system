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
	('Coltrane', 'Wood', '1982-06-15', '43 Allan Street, Baltimore', '54651434744', 'wood@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Malrick', 'Modeny', '1976-08-25', '82 Bermer Road, Winston', '15764234744', 'modeny@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Katy', 'Pank', '1991-10-13', '125 Winter Road, Denver', '86578646754', 'pank@geek.net', 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Wendy', 'Vinson', '1986-07-15', '94 Lake Street, Poukito', '42366738768', 'vinson@geek.net', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Biern', 'Nardik', '1981-02-19', '24 Malory Street, Svenson', '27643253434', 'nardik@geek.net', 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lyoto', 'Nuka', '1972-12-03', '432 Green Road, Mandon', '64453428585', 'nuka@geek.net', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Sven', 'Weis', '1977-01-20', '76 Wallder Avenue, Kent', '43456335457', 'weis@geek.net', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Gerhard', 'Schot', '1980-09-04', '25 Marlon Street, Arland', '83451833577', 'schot@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kendy', 'Moody', '1979-07-09', '74 Wauxhall Street, Boulver', '25437458732', 'moody@geek.net', 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lares', 'Arpag', '1983-05-02', '110 Teplon Road, Vrexham', '72465647845', 'arpag@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Walcot', 'Storn', '1976-08-22', '145 Orkan Street, Bulder', '54637566845', 'storn@geek.net', 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kasey', 'Gibber', '1983-11-17', '65 Oak Avenue, Courthall', '42357489378', 'gibber@geek.net', 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
	
INSERT INTO visits (patient_id, doctor_id, description, visit_date, visit_time, status, create_time, update_time) VALUES
	(1, 6, null, '2019-05-12', '14:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(1, 3, null, '2019-06-04', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(1, 5, null, '2019-07-16', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(1, 6, null, '2019-10-21', '10:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(1, 12, null, '2019-12-16', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(2, 7, null, '2019-03-12', '08:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(2, 9, null, '2019-08-21', '11:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(2, 2, null, '2019-11-09', '10:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(2, 1, null, '2020-03-14', '15:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(3, 10, null, '2019-10-17', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(3, 8, null, '2019-11-08', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(3, 3, null, '2020-05-10', '12:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(4, 3, null, '2019-02-04', '08:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(4, 7, null, '2019-09-27', '13:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(4, 5, null, '2020-01-30', '11:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(4, 11, null, '2020-02-17', '14:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(4, 1, null, '2020-04-06', '08:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(5, 6, null, '2019-10-03', '08:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(5, 3, null, '2019-12-12', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(5, 12, null, '2019-12-21', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(5, 9, null, '2020-01-05', '14:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 3, null, '2019-06-13', '11:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 9, null, '2019-08-28', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 10, null, '2019-09-07', '10:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 1, null, '2019-10-17', '11:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 4, null, '2019-12-04', '14:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 2, null, '2020-02-18', '15:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(6, 8, null, '2020-03-19', '09:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(7, 7, null, '2019-05-01', '10:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(7, 3, null, '2019-09-05', '08:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(7, 5, null, '2019-11-26', '14:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(7, 12, null, '2020-03-05', '13:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 6, null, '2019-04-02', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 1, null, '2019-09-18', '11:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 2, null, '2019-10-08', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 9, null, '2019-11-16', '15:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 10, null, '2019-12-24', '10:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 5, null, '2020-02-08', '08:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(8, 11, null, '2020-03-14', '12:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(9, 8, null, '2019-07-29', '13:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(9, 7, null, '2019-08-12', '11:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(9, 11, null, '2019-10-20', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(9, 12, null, '2019-12-03', '17:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(10, 2, null, '2019-01-06', '15:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(10, 6, null, '2019-07-27', '12:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(10, 3, null, '2019-10-11', '09:00', 'COMPLETED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(10, 7, null, '2020-03-29', '13:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	(1, 6, null, '2020-04-15', '16:00', 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users (username, password, first_name, last_name, create_time, update_time) VALUES
	('admin', '{bcrypt}$2b$10$e/b1Zz12zfjOBM9JfudgueTVvO7PhjBcQyUiyNbHcge0ifKM9Ht6u', 'Kiorn', 'Winuto', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
	
INSERT INTO users_roles (user_id, role_id) VALUES
	(1, 1);