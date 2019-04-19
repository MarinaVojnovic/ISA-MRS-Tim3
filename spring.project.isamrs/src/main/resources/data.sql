-- Inserting default airlines in database
INSERT INTO airline (id, address, name, promotional_description) 
VALUES (1, 'Aerodrom Beograd 59, 11180 Beograd', 'Aerodrom Nikola Tesla', 
		'Aerodrom da letis i sletis')
ON DUPLICATE KEY UPDATE id = 1;

-- Inserting default hotels in database
INSERT INTO hotel (id, address, name, promotional_description) 
VALUES (1, 'Kneza Milosa 1, Bijeljina 76300', 'Hotel Drina', 'Hotel da spavas i jedes')
ON DUPLICATE KEY UPDATE id = 1;

-- Inserting default rent-a-car services in database
INSERT INTO rentacar (id, address, name, promotional_description) 
VALUES (1, '9, 21000, Dr Svetislava Kasapinovica, Novi Sad', 'RENTACAR NOVI SAD DDM COMPANY'
		, 'Rentacar da uzmes auto i vozis')
ON DUPLICATE KEY UPDATE id = 1;

-- Inserting default system administrator in database
-- Encrypted password is '123'
INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'User', 1, 'sysadmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Marko', 'Markovic', 'markomarkovic@example.com', true, '2019-04-18 20:58:00', 
		'00381643332211')
ON DUPLICATE KEY UPDATE username = 'sysadmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (1, 'ROLE_SYSTEM_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_SYSTEM_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (1, 1)
ON DUPLICATE KEY UPDATE user_id = 1;

-- Inserting default airline administrator in database
-- Encrypted password is '123'
INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'AirlineAdmin', 2, 'airlineadmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikola', 'Nikolic', 'nikolanikolic@example.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = 'airlineadmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (2, 'ROLE_AIRLINE_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_AIRLINE_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (2, 2)
ON DUPLICATE KEY UPDATE user_id = 2;

-- Inserting default hotel administrator in database
-- Encrypted password is '123'
INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'HotelAdmin', 3, 'hoteladmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Stefan', 'Stefanovic', 'stefanstefanovic@example.com', true, '2019-04-18 20:58:00', 
		'00381648877666')
ON DUPLICATE KEY UPDATE username = 'hoteladmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (3, 'ROLE_HOTEL_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_HOTEL_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (3, 3)
ON DUPLICATE KEY UPDATE user_id = 3;

-- Inserting default rent-a-car service administrator in database
-- Encrypted password is '123'
INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RentacarAdmin', 4, 'rentacaradmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Vojin', 'Vojinovic', 'vojinvojinovic@example.com', true, '2019-04-18 20:58:00', 
		'00381649933777')
ON DUPLICATE KEY UPDATE username = 'rentacaradmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (4, 'ROLE_RENTACAR_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_RENTACAR_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (4, 4)
ON DUPLICATE KEY UPDATE user_id = 4;