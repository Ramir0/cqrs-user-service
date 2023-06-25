CREATE TABLE users
(
    id          VARCHAR(36) KEY,
    name        VARCHAR(255) NULL,
    lastname    VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    is_active   SMALLINT(1) NULL
);
