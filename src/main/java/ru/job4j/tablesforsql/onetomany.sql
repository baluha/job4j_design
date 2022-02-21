create table hobbies (
int serial primary key,
name varchar(255)
);

create table person (
int serial primary key,
name varchar(255),
hobby_id references hobbies(id)
);