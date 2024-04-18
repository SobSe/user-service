create schema if not exists user_management;

create table user_management.users(
    id serial primary key,
    name varchar(255),
    password varchar(255)
);

create table user_management.roles (
    id serial primary key,
    role varchar(255)
);

create table user_management.user_roles (
    id serial primary key,
    id_user int references user_management.users(id),
    id_role int references user_management.roles(id),
    constraint uk_user_roles unique (id_user, id_role)
);