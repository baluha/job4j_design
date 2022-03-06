insert into fauna(name, avg_age, discovery_date) values('fish', 20000, '1000-05-25');
insert into fauna(name, avg_age, discovery_date) values('fish1', 20000, '2000-05-25');
insert into fauna(name, avg_age, discovery_date) values('fish2', 10000, '199-05-25');
insert into fauna(name, avg_age, discovery_date) values('dog', 15000, '1425-05-25');
insert into fauna(name, avg_age, discovery_date) values('dog1', 12000, '1850-05-25');
insert into fauna(name, avg_age, discovery_date) values('cat1', 14000, '452-05-25');
insert into fauna(name, avg_age, discovery_date) values('cat2', 1000, '152-05-25');
insert into fauna(name, avg_age, discovery_date) values('cat3', 1500, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 20000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date >'1950-01-01';