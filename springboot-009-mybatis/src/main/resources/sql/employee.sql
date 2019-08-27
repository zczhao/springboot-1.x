CREATE TABLE IF NOT EXISTS employee (
    id int not null primary key AUTO_INCREMENT,
    last_name varchar(255),
    email varchar(255),
    gender int,
    dept_id int
);