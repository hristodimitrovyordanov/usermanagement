CREATE TABLE persons (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    u_password VARCHAR(50) NOT NULL,
    enabled INT NOT NULL
);

INSERT INTO persons (username, u_password, enabled)
VALUES
('ivan','{noop}ivan1234',1),
('george','{noop}george1234',1),
('hristo','{noop}hristo1234',1);