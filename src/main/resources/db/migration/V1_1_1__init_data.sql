insert into bank(id, bank_code, bank_money, bank_name) values (1, 'MYBANK', 1000000000, 'My Bank');

-- insert admin
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (1, 'admin@demo.com', 'admin', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'Admin', '0987654321', '123456789');
insert into user_role(id, role_name, user_id) values(1, 'ROLE_ADMIN', 1);

-- insert user 1
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (2, 'user@demo.com', 'user', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'User Demo', '0852369741', '852369741');
insert into user_role(id, role_name, user_id) values(2, 'ROLE_USER', 2);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(1, '159736842',  '2022-05-04', 1, 10000, 2);
insert into card(id, account_number, active_date, current_balance, user_id) values(1, '159736842', '2022-05-04', 10000, 2);

-- insert user 2
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (3, 'bin@gmail.com', 'bin', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'bin', '0852369742', '852369341');
insert into user_role(id, role_name, user_id) values(3, 'ROLE_USER', 3);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(2, '154566842', '2022-05-04', 1, 11110, 3);
insert into card(id, account_number, active_date, current_balance, user_id) values(2, '154566842', '2022-05-04', 11110, 3);

-- insert user 3
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (4, 'ken@gmail.com', 'ken', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'ken', '0852123742', '852368671');
insert into user_role(id, role_name, user_id) values(4, 'ROLE_USER', 4);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(3, '154123442', '2022-05-04', 1, 115410, 4);
insert into card(id, account_number, active_date, current_balance, user_id) values(3, '154123442', '2022-05-04', 115410, 4);

-- insert user 4
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (5, 'chi@gmail.com', 'chi', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'chi', '0854567742', '852334571');
insert into user_role(id, role_name, user_id) values(5, 'ROLE_USER', 5);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(4, '154123762', '2022-05-04', 1, 1234410, 5);
insert into card(id, account_number, active_date, current_balance, user_id) values(4, '154123442', '2022-05-04', 1234410, 5);

-- insert user 5
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (6, 'van@gmail.com', 'van', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'van', '0854234542', '812367871');
insert into user_role(id, role_name, user_id) values(6, 'ROLE_USER', 6);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(5, '165437762', '2022-05-04', 1, 1234434, 6);
insert into card(id, account_number, active_date, current_balance, user_id) values(5, '165437762', '2022-05-04', 1234434, 6);

-- insert user 6
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (7, 'nhan@gmail.com', 'nhan', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'nhan', '0854253442', '812323471');
insert into user_role(id, role_name, user_id) values(7, 'ROLE_USER', 7);
insert into account(account_id, account_number, active_date, bank_id, current_balance, user_id) values(6, '164654762', '2022-05-04', 1, 1245634, 7);
insert into card(id, account_number, active_date, current_balance, user_id) values(6, '165437762', '2022-05-04', 1245634, 7);

insert into loans_package(loans_id, duration, interest_rate) values (1, 24, 0.02);
insert into loans_package(loans_id, duration, interest_rate) values (2, 12, 0.01);
insert into loans_package(loans_id, duration, interest_rate) values (3, 6, 0.0015);

insert into saving_package(saving_package_id, duration, interest_rate) values (1, 24, 0.02);
insert into saving_package(saving_package_id, duration, interest_rate) values (2, 12, 0.01);
insert into saving_package(saving_package_id, duration, interest_rate) values (3, 6, 0.0015);
