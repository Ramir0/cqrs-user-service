INSERT INTO roles (id, name)
VALUES ('7ea89508-906f-11ee-b9d1-0242ac120002', 'Standard'),
       ('9abef656-906f-11ee-b9d1-0242ac120002', 'Administrator');

INSERT INTO permissions (id, name)
VALUES ('d64a9c98-906f-11ee-b9d1-0242ac120002', 'View Users'),
       ('f54bdf12-906f-11ee-b9d1-0242ac120002', 'Create Users'),
       ('07d236b8-9070-11ee-b9d1-0242ac120002', 'Update Users'),
       ('23ebdad4-9070-11ee-b9d1-0242ac120002', 'Delete Users');

INSERT INTO roles_permissions  (role_id, permission_id)
VALUES ('7ea89508-906f-11ee-b9d1-0242ac120002', 'd64a9c98-906f-11ee-b9d1-0242ac120002'),
       ('9abef656-906f-11ee-b9d1-0242ac120002', 'd64a9c98-906f-11ee-b9d1-0242ac120002'),
       ('9abef656-906f-11ee-b9d1-0242ac120002', 'f54bdf12-906f-11ee-b9d1-0242ac120002'),
       ('9abef656-906f-11ee-b9d1-0242ac120002', '07d236b8-9070-11ee-b9d1-0242ac120002'),
       ('9abef656-906f-11ee-b9d1-0242ac120002', '23ebdad4-9070-11ee-b9d1-0242ac120002');
