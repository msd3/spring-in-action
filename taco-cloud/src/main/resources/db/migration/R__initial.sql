create table ingredients (
    id uuid primary key,
    name varchar(256) not null
);

insert into ingredients (id, name) values ('9cf5d637-9c74-43ae-89f5-c6adfad8464c', 'Carrot');
insert into ingredients (id, name) values ('c3de3d46-e5fd-4e71-8dad-e41a93c5d08b', 'Beans');