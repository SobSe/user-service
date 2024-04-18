insert into user_management.users
(name, password)
values
    ('usertest', '{noop}password');

insert into user_management.user_roles
(id_user, id_role)
values
    (3, 2);