create table products (
int serial primary key,
name varchar(255)
);

create table persons (
int serial primary key,
name varchar(255)
);

create table orders (
int serial primary key,
product_id int references priducts(id),
persons_id int references persons(id)
);