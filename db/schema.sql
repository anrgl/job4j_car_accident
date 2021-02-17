create table accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id int references type(id)
);

create table type (
    id serial primary key,
    name varchar(500)
);

create table rule (
    id serial primary key,
    name varchar(255)
);

create table accident_rule (
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references rule(id)
);