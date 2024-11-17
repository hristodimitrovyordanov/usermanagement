CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL REFERENCES persons (username),
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT unique_username_authority UNIQUE (username, authority)
);

INSERT INTO authorities (username, authority)
VALUES
('ivan','ROLE_EMPLOYEE'),
('george','ROLE_EMPLOYEE'),
('george','ROLE_MANAGER'),
('hristo','ROLE_EMPLOYEE'),
('hristo','ROLE_MANAGER'),
('hristo','ROLE_ADMIN');