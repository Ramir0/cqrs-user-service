ALTER TABLE users ADD role_id VARCHAR(36) REFERENCES roles(id);
