insert into user_management.roles
    (role)
values
    ('ROLE_ADMIN'),
    ('ROLE_USER');

insert into user_management.users
    (name, password)
values
    ('admin', '{noop}password'),
    ('user', '{noop}password');

insert into user_management.user_roles
    (id_user, id_role)
values
    (1, 1),
    (2, 2);