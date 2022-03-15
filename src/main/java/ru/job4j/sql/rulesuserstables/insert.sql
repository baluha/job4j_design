insert into role(name) values('горнорабочий');
insert into role(name) values('проходчик');
insert into role(name) values('мастер горный');

insert into rule(name) values('зачистка');
insert into rule(name) values('подвоз материалов');
insert into rule(name) values('крепление');
insert into rule(name) values('оборка кровли');
insert into rule(name) values('выдача наряда в шахте');
insert into rule(name) values('надзор исполнения наряда');

insert into category(value) values('срочная');
insert into category(value) values('важная');
insert into category(value) values('неважная');

insert into roles_and_rules(role_id, rule_id) values(1, 1);
insert into roles_and_rules(role_id, rule_id) values(1, 2);
insert into roles_and_rules(role_id, rule_id) values(2, 1);
insert into roles_and_rules(role_id, rule_id) values(2, 2);
insert into roles_and_rules(role_id, rule_id) values(2, 3);
insert into roles_and_rules(role_id, rule_id) values(2, 4);
insert into roles_and_rules(role_id, rule_id) values(3, 5);
insert into roles_and_rules(role_id, rule_id) values(3, 6);

insert into state(name) values('Задача поставлена');
insert into state(name) values('Задача выполняется');
insert into state(name) values('Задача выполнена');

insert into users(name, role_id) values('Иван', 1);
insert into users(name, role_id) values('Василий', 2);
insert into users(name, role_id) values('Николай', 2);
insert into users(name, role_id) values('Роман', 2);
insert into users(name, role_id) values('Сергей', 3);

insert into item(name, users_id, category_id, state_id)
values('подвезти материалы в забой', 1, 1, 1);
insert into item(name, users_id, category_id, state_id)
values('Дать доп наряд', 5, 2, 3);

insert into comments(comment, item_id) values('задача невыполнима!', 2);
insert into comments(comment, item_id) values('требуется помощь', 1);

insert into attachs(sometext, item_id) values('описание наряда 1', 1);
insert into attachs(sometext, item_id) values('описание наряда 2', 2);