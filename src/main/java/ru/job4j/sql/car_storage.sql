create table body (
id serial primary key,
name varchar(255)
);

create table engine (
id serial primary key,
name varchar(255)
);

create table transmission (
id serial primary key,
name varchar(255)
);

create table car (
id serial primary key,
name varchar(255),
body_id integer references body(id),
engine_id integer references engine(id),
transmission_id integer references transmission(id)
);

insert into body(name) values ('sedan');
insert into body(name) values ('koupe');
insert into body(name) values ('krossover');
insert into body(name) values ('hatchback');

insert into engine(name) values ('dizel');
insert into engine(name) values ('petrol');
insert into engine(name) values ('electric');

insert into transmission(name) values ('mechenical');
insert into transmission(name) values ('automat');
insert into transmission(name) values ('robotic');
insert into transmission(name) values ('турбомуфта');

insert into car(name, body_id, engine_id, transmission_id)
 values ('Opel', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id)
 values ('Audi', 2, 2, 3);
insert into car(name, body_id, engine_id, transmission_id)
 values ('Skoda', 3, 1, 3);
insert into car(name, body_id, engine_id, transmission_id)
 values ('Seat', 2, 2, 1);
insert into car(name, body_id, engine_id, transmission_id)
 values ('Volkswagen', 2, 2, 2);
 insert into car(name, body_id, engine_id, transmission_id)
 values ('ВАЗ', null, null, null);

select c.name as Название_машины, b.name as Кузов,
e.name as Двигатель, t.name as Коробка
from car as c
left join body as b
on c.body_id = b.id
left join engine as e
on c.engine_id = e.id
left join transmission as t
on c.transmission_id = t.id

select b.name as Кузов
from body as b
left join car as c
on b.id = c.body_id
where c.body_id is null;

select e.name as Двигатель
from engine as e
left join car as c
on e.id = c.engine_id
where c.engine_id is null;

select t.name as Коробка
from transmission as t
left join car as c
on t.id = c.transmission_id
where c.transmission_id is null;



