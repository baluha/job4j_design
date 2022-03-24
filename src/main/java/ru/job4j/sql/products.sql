create table product (
id serial primary key,
	name varchar(255),
	type_id integer references type(id),
	expired_date date,
	price float
);

create table type (
id serial primary key,
	name varchar(255)
);


insert into type (name) values ('сыр');
insert into type (name) values ('молоко');
insert into type (name) values ('конфеты');
insert into type (name) values ('печенье');
insert into type (name) values ('мороженое');


insert into product (name, type_id, expired_date, price)
values ('сыр московский', 1, '31-12-22', 2.0);
insert into product (name, type_id, expired_date, price)
values ('сыр моцарелла', 1, '31-01-22', 4.0);
insert into product (name, type_id, expired_date, price)
values ('сыр пармезан', 1, '08-02-22', 5.0);
insert into product (name, type_id, expired_date, price)
values ('молоко вятушка', 2, '28-03-22', 1.0);
insert into product (name, type_id, expired_date, price)
values ('молоко селянское', 2, '12-01-22', 8.0);
insert into product (name, type_id, expired_date, price)
values ('молоко коровье', 2, '13-10-22', 9.0);
insert into product (name, type_id, expired_date, price)
values ('молоко птичье', 2, '24-04-22', 10.0);
insert into product (name, type_id, expired_date, price)
values ('печенье', 4, '22-04-99', 11.0);
insert into product (name, type_id, expired_date, price)
values ('конфеты', 3, '11-04-22', 50.0);
insert into product (name, type_id, expired_date, price)
values ('печенье нота', 4, '22-04-99', 11.0);
insert into product (name, type_id, expired_date, price)
values ('конфеты рачки', 5, '11-04-22', 50.0);
insert into product (name, type_id, expired_date, price)
values ('мороженое', 5, '11-05-22', 50.0);
insert into product (name, type_id, expired_date, price)
values ('мороженое жанна', 5, '11-04-23', 50.0);

select * from product
join type t
on product.type_id = t.id
where t.name = 'сыр'

select * from product
where product.name like '%мороженое%'

select * from product
where product.expired_date < CURRENT_TIMESTAMP

select max(price) from product;

select t.name, count(t.name)
from product p
join type as t
on p.type_id = t.id
group by t.name;

select p.name
from product p
join type as t
on p.type_id = t.id
where t.name like 'сыр' OR t.name like 'молоко';

select t.name
from product p
join type as t
on p.type_id = t.id
group by t.id
having count(t.name) < 4;

select p.name, t.name
from product p
join type as t
on p.type_id = t.id
