create database Registration;
use Registration;

create table register
(id int primary key not null auto_increment,
firstname varchar(30),
lastname varchar(30),
username varchar(30),
password varchar(30));

select * from register;
