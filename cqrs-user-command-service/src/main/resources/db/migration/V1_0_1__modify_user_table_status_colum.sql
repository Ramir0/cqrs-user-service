ALTER TABLE users ADD status TINYINT;

UPDATE users
SET status = 1
WHERE is_active = 1;

UPDATE users
SET status = 2
WHERE is_active = 0;

ALTER TABLE users DROP COLUMN is_active;
