create TABLE students (
    student_id int not null AUTO_INCREMENT primary key ,
    name varchar(50) not null,
    email varchar(50) not null unique,
    password varchar(50) not null,
    degree varchar(50) not null,
    branch varchar(50) not null,
    semester varchar(50) not null
);