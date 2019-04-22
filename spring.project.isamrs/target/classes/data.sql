-- Inserting default airlines in database
INSERT INTO airline (id, address, name, promotional_description) 
VALUES (1, 'Aerodrom Beograd 59, 11180 Beograd', 'Aerodrom Nikola Tesla', 
		'Aerodrom da letis i sletis')
ON DUPLICATE KEY UPDATE id = 1;

INSERT INTO airline (id, address, name, promotional_description) 
VALUES (2, 'Aerodrom Beograd 59, 11180 Beograd', 'Aerodrom Nikola Teslaa', 
		'Aerodrom da letis i sletis')
ON DUPLICATE KEY UPDATE id = 2;

INSERT INTO airline (id, address, name, promotional_description) 
VALUES (3, 'Aerodrom Beograd 59, 11180 Beograd', 'Aerodrom Nikola Teslaaa', 
		'Aerodrom da letis i sletis')
ON DUPLICATE KEY UPDATE id = 3;

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
					last_password_reset_date, phone_number, first_time) 
VALUES ( 'User', 1, 'sysadmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Marko', 'Markovic', 'markomarkovic@example.com', true, '2019-04-18 20:58:00', 
		'00381643332211', true)
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
					last_password_reset_date, phone_number, first_time, airline_id) 
VALUES ( 'AirlineAdmin', 2, 'airlineadmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikola', 'Nikolic', 'nikolanikolic@example.com', true, '2019-04-18 20:58:00', 
		'00381645544333', true, 1)
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
					last_password_reset_date, phone_number, first_time, hotel_id) 
VALUES ( 'HotelAdmin', 3, 'hoteladmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Stefan', 'Stefanovic', 'stefanstefanovic@example.com', true, '2019-04-18 20:58:00', 
		'00381648877666', true, 1)
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
					last_password_reset_date, phone_number, first_time, rentacar_id) 
VALUES ( 'RentacarAdmin', 4, 'rentacaradmin', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Vojin', 'Vojinovic', 'vojinvojinovic@example.com', true, '2019-04-18 20:58:00', 
		'00381649933777', true, 1)
ON DUPLICATE KEY UPDATE username = 'rentacaradmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (4, 'ROLE_RENTACAR_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_RENTACAR_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (4, 4)
ON DUPLICATE KEY UPDATE user_id = 4;

INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RegularUser', 5, '123', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikola', 'Nikolic', 'nikolanikolic@example.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = '123';
INSERT INTO AUTHORITY (id, name)
VALUES (5, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (5, 5)
ON DUPLICATE KEY UPDATE user_id = 5;


INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RegularUser', 6, '124', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikolina', 'Nikolic', 'nikolanikolic@example.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = '124';
INSERT INTO AUTHORITY (id, name)
VALUES (6, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (6, 6)
ON DUPLICATE KEY UPDATE user_id = 6;

INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RegularUser', 7, '125', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Ana', 'Nikolic', 'nikolanikolic@example.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = '125';
INSERT INTO AUTHORITY (id, name)
VALUES (7, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (7, 7)
ON DUPLICATE KEY UPDATE user_id = 7;
