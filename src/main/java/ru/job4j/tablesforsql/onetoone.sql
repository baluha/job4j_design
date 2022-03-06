create table adress (
id serial primary key,
city varchar(255)
);

create table person (
id serial primary key,
name varchar(255),
adress_id int references adress(id) unique
);