INSERT INTO patients (first_name, last_name, birth_date, address, phone_number, email, create_time, update_time) VALUES
	('Clementine', 'Bauch', '1981-05-21', '533 Monument Walk, Enetai, Illinois, 6436', '1-463-123-4447', 'nathan@yesenia.net', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Tami', 'Underwood', '1992-07-28', '533 Monument Walk, Enetai, Illinois, 6436', '2-673-132-3437', 'elliscarr@senmei.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Chelsey', 'Dietrich', '1972-11-12', '533 Monument Walk, Enetai, Illinois, 6436', '1-446-653-5554', 'denisedorsey@kidstock.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Dennis', 'Schulist', '1989-02-17', '533 Monument Walk, Enetai, Illinois, 6436', '6-764-157-3436', 'myrtleryan@geekmosis.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Considine', 'Lockman', '1961-10-05', '533 Monument Walk, Enetai, Illinois, 6436', '7-765-145-4665', 'avilarhodes@uberlux.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kurtis', 'Weissnat', '1992-04-02', '533 Monument Walk, Enetai, Illinois, 6436', '3-463-675-5777', 'tamiunderwood@duflex.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Nicholas', 'Runolfsdottir', '1991-03-04', '533 Monument Walk, Enetai, Illinois, 6436', '6-665-156-4567', 'antoinettemason@vendblend.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Glenna', 'Reichert', '1974-12-16', '533 Monument Walk, Enetai, Illinois, 6436', '2-473-853-6756', 'rey.padberg@karina.biz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Clementina', 'DuBuque', '1975-01-11', '533 Monument Walk, Enetai, Illinois, 6436', '8-473-127-6454', 'sherwood@rosamond.me', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Ellis', 'Carr', '1984-06-25', '533 Monument Walk, Enetai, Illinois, 6436', '1-653-875-6866', 'telly.hoeger@billy.biz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO specialties (name) values
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
	('Coltrane', 'Wood', '1982-06-15', '43 Walker Street, Baltimore', '5-465-143-4744', 'wood@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Malrick', 'Modeny', '1976-08-25', '43 Walker Street, Baltimore', '1-576-423-4744', 'wood@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Katy', 'Pank', '1991-10-13', '43 Walker Street, Baltimore', '8-657-864-6754', 'wood@geek.net', 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Wendy', 'Vinson', '1986-07-15', '43 Walker Street, Baltimore', '4-236-673-8768', 'wood@geek.net', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Biern', 'Nardik', '1981-02-19', '43 Walker Street, Baltimore', '2-764-325-3434', 'wood@geek.net', 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lyoto', 'Nuka', '1972-12-03', '43 Walker Street, Baltimore', '6-445-342-8585', 'wood@geek.net', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Sven', 'Weis', '1977-01-20', '43 Walker Street, Baltimore', '4-345-633-5457', 'wood@geek.net', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Gerhard', 'Schot', '1980-09-04', '43 Walker Street, Baltimore', '8-345-183-3577', 'wood@geek.net', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Kendy', 'Moody', '1979-07-09', '43 Walker Street, Baltimore', '2-543-745-8732', 'wood@geek.net', 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
	('Lares', 'Arpag', '1983-05-02', '43 Walker Street, Baltimore', '7-246-564-7845', 'wood@geek.net', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
	