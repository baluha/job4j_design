create table products (
id serial primary key,
name varchar(255)
);

create table persons (
id serial primary key,
name varchar(255)
);

create table orders (
id serial primary key,
product_id int references products(id),
persons_id int references persons(id)
);