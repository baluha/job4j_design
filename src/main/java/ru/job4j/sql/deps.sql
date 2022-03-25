create table departments (
id serial primary key,
name varchar(255)
);

create table emploers (
id serial primary key,
name varchar(255),
departments_id integer references departments(id)
);

insert into departments(name) values ('D1');
insert into departments(name) values ('D2');
insert into departments(name) values ('D3');
insert into departments(name) values ('D4');

insert into emploers(name, departments_id) values ('Ivan Borisov', 1);
insert into emploers(name, departments_id) values ('Roman Venokurov', 1);
insert into emploers(name, departments_id) values ('Ramzan Kadirov', 2);
insert into emploers(name, departments_id) values ('Azamat Temirazev', 2);
insert into emploers(name, departments_id) values ('Bratislav Ivushkin', 3);
insert into emploers(name, departments_id) values ('Stanislav Prohorov', 3);

select * from departments as d
left join emploers as e
on e.departments_id = d.id

select * from departments as d
right join emploers as e
on e.departments_id = d.id

select * from departments as d
full join emploers as e
on e.departments_id = d.id

select * from departments d cross join emploers e;

select d.name, e.name
from emploers as e
left join departments as d
on e.departments_id = d.id;

select d.name, e.name
from emploers as e
right join departments as d
on e.departments_id = d.id;

select d.name, e.name
from departments as d
left join emploers as e
on e.departments_id = d.id
where departments_id is null;

select d.name, e.name
from emploers as e
left join departments as d
on e.departments_id = d.id;

select d.name, e.name
from departments as d
right join emploers as e
on d.id = e.departments_id;

create table teens (
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens(name, gender) values('Roman', 'male');
insert into teens(name, gender) values('Ivan', 'male');
insert into teens(name, gender) values('Ruslan', 'male');
insert into teens(name, gender) values('Inna', 'female');
insert into teens(name, gender) values('Olga', 'female');
insert into teens(name, gender) values('Julia', 'female');