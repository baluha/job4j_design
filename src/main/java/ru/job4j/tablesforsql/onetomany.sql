create table hobbies (
id serial primary key,
name varchar(255)
);

create table person (
id serial primary key,
name varchar(255),
hobby_id references hobbies(id)
);