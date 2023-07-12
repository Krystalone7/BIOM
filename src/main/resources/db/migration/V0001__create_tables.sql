drop table if exists biom.user_role;
drop table if exists biom.role;
drop table if exists biom.user;

create table biom.user
(
    id        bigserial primary key,
    username  varchar(25) not null,
    name      varchar(25) not null,
    surname   varchar(25) not null,
    info      varchar(200),
    phone     varchar(25),
    email     varchar(100) not null,
    password  varchar(100) not null
);
create table biom.role
(
    id int primary key,
    name varchar(25) not null
);
create table biom.user_role
(
    id serial primary key,
    user_id bigserial not null
        references biom.user(id),
    role_id bigserial not null
        references biom.role(id)
);
insert into biom.role
values
    (1, 'admin'),
    (2, 'user');