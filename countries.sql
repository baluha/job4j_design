create table countries (
id serial primary key,
	name varchar,
	square numeric,
	population integer
);

insert into countries(name, square, population) values
('USA', 100.1, 300),
('Russia', 120.2, 150),
('Ukraine', 80.8, 40);

update countries set name = 'Germany';
delete from countries;