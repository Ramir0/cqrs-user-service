CREATE TABLE roles (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE permissions (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE roles_permissions (
    role_id VARCHAR(36),
    permission_id VARCHAR(36),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);
