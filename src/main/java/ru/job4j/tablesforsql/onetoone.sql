create table adress (
int serial primary key,
city varchar(255)
);

create table person (
int serial primary key,
adress_id int references adress(id) unique
);