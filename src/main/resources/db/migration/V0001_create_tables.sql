drop table if exists biom.user;
drop table if exists biom.roles;
drop table if exists biom.user_roles;


create table biom.user
(
    id        bigserial primary key,
    username varchar(25) not null,
    name      varchar(25) not null,
    surname   varchar(25) not null,
    birthdate date,
    info      varchar(200),
    hobbies   varchar(200),
    phone     varchar(25),
    email     varchar(100),
    password  varchar(25) not null,
    role_id integer not null
);
create table biom.roles
(
    id bigserial primary key,
    name varchar(25) not null
);
create table biom.user_role
(
    user_id bigserial,
    role_id bigserial
);

insert into biom.user(username, name, surname, password, role_id) values('user', 'darya', 'a', '12345', 1);