-- Inserting default airlines in database
INSERT INTO airline (id, address, name, promotional_description, grade_number, score, city) 
VALUES (1, 'Aerodrom Beograd 59, 11180 Beograd', 'Aerodrom Nikola Tesla', 
		'Aerodrom da letis i sletis', 0, 0.0, 'Beograd')
ON DUPLICATE KEY UPDATE id = 1;

INSERT INTO airline (id, address, name, promotional_description, grade_number, score, city) 
VALUES (2, 'Paris Roissy Charles de Gaulle Airport BP 20101 95711, Roissy France', 'Aerodrom u Parizu', 
		'Aerodrom da letis i sletis', 0, 0.0, 'Paris')
ON DUPLICATE KEY UPDATE id = 2;

INSERT INTO airline (id, address, name, promotional_description, grade_number, score, city) 
VALUES (3, 'The Compass Centre, Nelson Road,
Hounslow, Middlesex, TW6 2GW', 'Aerodrom U Londonu', 
		'Aerodrom da letis i sletis', 0, 0.0, 'London')
ON DUPLICATE KEY UPDATE id = 3;

-- Inserting default hotels in database
INSERT INTO hotel (id, address, name, promotional_description, grade_number, score, city, version) 
VALUES (1, 'Kneza Milosa 1, Bijeljina 76300', 'Hotel Drina', 'Hotel da spavas i jedes', 0, 0.0, 'Bijeljina', 0)
ON DUPLICATE KEY UPDATE id = 1;

insert INTO room(id, grade_number,number_people, price, room_number, score, hotel_id)
values (101, 0, 4, 50, 1, 0, 1) ON DUPLICATE KEY UPDATE id = 101;

insert INTO room(id, grade_number,number_people, price, room_number, score, hotel_id)
values (102, 0, 3, 70, 2, 0, 1) ON DUPLICATE KEY UPDATE id = 102;

-- Inserting default rent-a-car services in database
INSERT INTO rentacar (id, address, name, promotional_description, number, score, city) 
VALUES (1, '9, 21000, Dr Svetislava Kasapinovica, Novi Sad', 'RENTACAR NOVI SAD DDM COMPANY'
		, 'Rentacar da uzmes auto i vozis', 0, 0.0, 'Novi Sad')
ON DUPLICATE KEY UPDATE id = 1;

insert INTO car(id, brand, car_type, fast_res_end_date, fast_res_price, fast_res_start_date, model, name, number, on_fast_res, price, score, seats, version, car_year, rentacar_id)
values (104, 'Renault', 'Family', '2019-06-30', 30, '2019-08-30', '5.0', 'Clio', 2, true, 100, 10, 5, 1, 2015,  1) ON DUPLICATE KEY UPDATE id = 104;

insert INTO car(id, brand, car_type, model, name, number, on_fast_res, price, score, seats, version, car_year, rentacar_id)
values (100, 'Jaguar', 'Sports', '15.0', 'F-Pace', 2, false, 300, 10, 2, 1, 2018,  1) on duplicate key update id= 100;

insert INTO car(id, brand, car_type, model, name, number, on_fast_res, price, score, seats, version, car_year, rentacar_id)
values (101, 'Audi', 'Family', '16.0', 'A5', 3, false, 200, 12, 5, 1, 2019,  1) on duplicate key update id=101;

insert INTO car(id, brand, car_type, model, name, number, on_fast_res, price, score, seats, version, car_year, rentacar_id)
values (102, 'Renault', 'Van', '6.0', 'Master', 0, false, 100, 0, 2, 1, 2013,  1) on  duplicate key update id=102;
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
		'00381645544333', true, 2)
ON DUPLICATE KEY UPDATE username = 'airlineadmin';
INSERT INTO AUTHORITY (id, name) 
VALUES (2, 'ROLE_AIRLINE_ADMIN')
ON DUPLICATE KEY UPDATE name = 'ROLE_AIRLINE_ADMIN';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (2, 2)
ON DUPLICATE KEY UPDATE user_id = 2;

insert into airline_working_destinations(id,airline_id,works_id) values (1,2,3) on duplicate key update id=1;
insert into airline_working_destinations(id,airline_id,works_id) values (2,2,1) on duplicate key update id=2;
insert INTO flight(id, cost, date_of_end,date_of_start,grade_number,length_of_flight,num_of_stops,number,score,airline_id,final_id,start_id)
values (100, 200, '2019-07-20 03:00', '2019-07-20 01:00', 0, 120, 0, 123, 0, 2, 1, 2) on duplicate key update id=100;

insert INTO flight(id, cost, date_of_end,date_of_start,grade_number,length_of_flight,num_of_stops,number,score,airline_id,final_id,start_id)
values (101, 300, '2019-08-20 03:00', '2019-08-20 01:00', 0, 120, 0, 123, 0, 2, 3, 2) on duplicate key update id=101;

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
VALUES ( 'RegularUser', 5, 'user123', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikola', 'Nikolic', 'leona.nedeljkovic@gmail.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = 'user123';
INSERT INTO AUTHORITY (id, name)
VALUES (5, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (5, 5)
ON DUPLICATE KEY UPDATE user_id = 5;


INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RegularUser', 6, 'user124', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Nikolina', 'Nikolic', 'leona.nedeljkovic@gmail.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = 'user124';
INSERT INTO AUTHORITY (id, name)
VALUES (6, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (6, 6)
ON DUPLICATE KEY UPDATE user_id = 6;

INSERT INTO USERS (dtype, id, username, password, first_name, last_name, email, enabled,
					last_password_reset_date, phone_number) 
VALUES ( 'RegularUser', 7, 'user125', 
		'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
		'Ana', 'Nikolic', 'leona.nedeljkovic@gmail.com', true, '2019-04-18 20:58:00', 
		'00381645544333')
ON DUPLICATE KEY UPDATE username = 'user125';
INSERT INTO AUTHORITY (id, name)
VALUES (7, 'ROLE_USER')
ON DUPLICATE KEY UPDATE name = 'ROLE_USER';
INSERT INTO USER_AUTHORITY (user_id, authority_id) 
VALUES (7, 7)
ON DUPLICATE KEY UPDATE user_id = 7;

insert into branch_office(id, address, city, rentacar_id)
values (100, 'Prvomajska 88', 'Beograd', 1)
ON DUPLICATE KEY UPDATE id = 100;

insert into branch_office(id, address, city, rentacar_id)
values (101, 'Balzakova 88', 'Novi Sad', 1)
ON DUPLICATE KEY UPDATE id = 101;

