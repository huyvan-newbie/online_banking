create table account (account_id bigint not null auto_increment, account_number varchar(255), active_date datetime, bank_id bigint, current_balance decimal(19,2), user_id bigint, primary key (account_id)) ;
create table bank (id bigint not null auto_increment, bank_code varchar(255), bank_money bigint, bank_name varchar(255), primary key (id)) ;
create table card (id bigint not null auto_increment, account_number varchar(255), active_date datetime, current_balance decimal(19,2), user_id bigint, primary key (id)) ;
create table loans (loan_id bigint not null, ssn varchar(255), loans_amount_repaid varchar(255), loans_amount_taken varchar(255), primary key (loan_id)) ;
create table loans_package (loans_id bigint not null auto_increment, duration varchar(255), interest_rate double precision, primary key (loans_id)) ;

create table saving_package (saving_package_id bigint not null auto_increment, duration varchar(255), interest_rate double precision, primary key (saving_package_id)) ;


create table persistent_logins (series varchar(255) not null, last_used datetime, token varchar(255), username varchar(255), primary key (series)) ;
create table transaction (transaction_id bigint not null auto_increment, description varchar(255), recipient_account_id bigint, status varchar(255), transaction_amount decimal(19,2) not null, transaction_date datetime, transaction_type varchar(255), account_id bigint, user_id bigint, primary key (transaction_id)) ;
create table user (id bigint not null auto_increment, city varchar(255), district varchar(255), email varchar(255), encrypted_password varchar(255), full_name varchar(255), password varchar(255), phone_number varchar(255), role varchar(255), ssn varchar(255), street_address varchar(255), username varchar(255), primary key (id)) ;
create table user_role (id bigint not null auto_increment, role_name varchar(255), user_id bigint, primary key (id)) ;
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UK_4bgmpi98dylab6qdvf9xyaxu4 unique (phone_number);
alter table user add constraint UK_o14ahnprxuevixmhn8cg59s4d unique (ssn);
alter table transaction add constraint FK6g20fcr3bhr6bihgy24rq1r1b foreign key (account_id) references account (account_id);
alter table transaction add constraint FKsg7jp0aj6qipr50856wf6vbw1 foreign key (user_id) references user (id);