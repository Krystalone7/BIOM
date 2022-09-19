DROP TABLE IF EXISTS user_profile;

create table user_profile
(
    id bigserial primary key,
    name varchar(25) not null,
    surname varchar(25) not null,
    birthdate date not null,
    info varchar(200) not null,
    hobbies varchar(200) not null,
    phone varchar(25) not null
);