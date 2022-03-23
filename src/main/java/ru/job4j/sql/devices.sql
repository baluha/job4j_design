insert into devices(name, price) values('xiaomi', 3000);
insert into devices(name, price) values('apple', 10000);
insert into devices(name, price) values('samsung', 7000);
insert into devices(name, price) values('huawei', 4000);
insert into devices(name, price) values('oppo', 3500);
insert into devices(name, price) values('nokia', 4500);

insert into people(name) values('Иван');
insert into people(name) values('Роман');
insert into people(name) values('Рустам');
insert into people(name) values('Егор');
insert into people(name) values('Александр');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(4, 4);
insert into devices_people(device_id, people_id) values(5, 1);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(4, 3);
insert into devices_people(device_id, people_id) values(5, 5);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;