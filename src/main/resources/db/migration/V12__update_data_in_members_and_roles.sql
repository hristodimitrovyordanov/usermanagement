ALTER TABLE roles DROP CONSTRAINT authorities_username_fkey;

UPDATE members
SET
    username = CASE
        WHEN username = 'ivan' THEN 'employee'
        WHEN username = 'george' THEN 'manager'
        WHEN username = 'hristo' THEN 'admin'
    END,
    password = CASE
        WHEN username = 'ivan' THEN '{bcrypt}$2a$12$kX/jf6RBTxANJSE8AyjJ.eua9U9JFL1dNAl4xtdhKwToMZKmn9/PS'
        WHEN username = 'george' THEN '{bcrypt}$2a$12$7qwiseqkQtovGh3x06iUieQbBNuqBdBTYgcUfuD7VqxRC4UeYCfLC'
        WHEN username = 'hristo' THEN '{bcrypt}$2a$12$p73q9dYI/4o9RdO90zhzuePtTUNnHs9zAfPS6lId6QkiWB9APHa/e'
    END
WHERE username IN ('ivan', 'george', 'hristo');

UPDATE roles
SET username = CASE
    WHEN username = 'ivan' THEN 'employee'
    WHEN username = 'george' THEN 'manager'
    WHEN username = 'hristo' THEN 'admin'
END
WHERE username IN ('ivan', 'george', 'hristo');

ALTER TABLE roles
ADD CONSTRAINT authorities_username_fkey
FOREIGN KEY (username)
REFERENCES members (username);