create database jaishreeram;
use jaishreeram;

create table queries(
    id int primary key auto_increment,
    name varchar(100) not null,
    email varchar(150) not null,
    phone varchar(25) not null,
    message varchar(500) not null
);
