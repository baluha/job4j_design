create table shop (
id serial primary key,
	name varchar(64)
);

create table cats (
name varchar(64),
	 age int,
	shop_id int references shop(id)
);

insert into shop(name) values('shop1');
insert into shop(name) values('shop2');
insert into shop(name) values('shop3');
insert into shop(name) values('shop4');

insert into cats(name, age, shop_id) values('cat1', 4, 1);
insert into cats(name, age, shop_id) values('cat2', 2, 1);
insert into cats(name, age, shop_id) values('cat3', 3, 2);
insert into cats(name, age, shop_id) values('cat4', 8, 4);
insert into cats(name, age, shop_id) values('cat5', 5, 4);

SELECT ct.name as Кличка, sh.name as Магазин
from cats as ct
join shop as sh
on ct.shop_id = sh.id;

SELECT ct.name as Имя, ct.age as Возраст, sh.name as Магазин
from cats as ct
join shop as sh
on ct.shop_id = sh.id;

SELECT ct.name as Кличка, ct.age as Возраст, sh.name as Название_магазина, sh.id as ID
from cats as ct
join shop as sh
on ct.shop_id = sh.id;