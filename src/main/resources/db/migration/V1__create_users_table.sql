DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE
);

WITH first_names AS (
    SELECT unnest(ARRAY[
        'John', 'Jane', 'Alice', 'Bob', 'Mary', 'Tom', 'Lucy', 'Mark', 'Emma', 'Jake',
        'Olivia', 'Liam', 'Noah', 'Sophia', 'Mason', 'Ethan', 'Isabella', 'Ava', 'Mia', 'James',
        'Charlotte', 'Benjamin', 'Elijah', 'Amelia', 'Lucas', 'Harper', 'Henry', 'Ella', 'Michael', 'Grace',
        'Daniel', 'Victoria', 'Samuel', 'Scarlett', 'Madison', 'David', 'Emily', 'Matthew', 'Chloe', 'Evelyn',
        'Anthony', 'Zoey', 'Dylan', 'Hannah', 'Carter', 'Lily', 'Christopher', 'Ella', 'Nathan', 'Aubrey',
        'Gabriel', 'Layla', 'Jonathan', 'Riley', 'Ryan', 'Zoe', 'Andrew', 'Savannah', 'Nicholas', 'Leah',
        'Connor', 'Aria', 'Joshua', 'Sofia', 'Adam', 'Brooklyn', 'Eli', 'Claire', 'Isaac', 'Skylar',
        'Owen', 'Penelope', 'Caleb', 'Aaliyah', 'Hunter', 'Aurora', 'Levi', 'Paisley', 'Aaron', 'Nora'
    ]) AS first_name
),
last_names AS (
    SELECT unnest(ARRAY[
        'Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Miller', 'Davis', 'Garcia', 'Rodriguez', 'Wilson',
        'Martinez', 'Anderson', 'Taylor', 'Thomas', 'Moore', 'Martin', 'Jackson', 'Thompson', 'White', 'Harris',
        'Clark', 'Lewis', 'Robinson', 'Walker', 'Young', 'Allen', 'King', 'Wright', 'Scott', 'Green',
        'Baker', 'Adams', 'Nelson', 'Hill', 'Ramirez', 'Campbell', 'Mitchell', 'Roberts', 'Carter', 'Phillips',
        'Evans', 'Turner', 'Torres', 'Parker', 'Collins', 'Edwards', 'Stewart', 'Flores', 'Morris', 'Nguyen',
        'Murphy', 'Rivera', 'Cook', 'Rogers', 'Morgan', 'Peterson', 'Cooper', 'Reed', 'Bailey', 'Bell',
        'Gomez', 'Kelly', 'Howard', 'Ward', 'Cox', 'Diaz', 'Richardson', 'Wood', 'Watson', 'Brooks',
        'Bennett', 'Gray', 'James', 'Reyes', 'Cruz', 'Hughes', 'Price', 'Myers', 'Long', 'Foster',
        'Sanders', 'Ross', 'Morales', 'Powell', 'Sullivan', 'Russell', 'Ortiz', 'Jenkins', 'Gutierrez', 'Perry'
    ]) AS last_name
),

generated_users AS (
    SELECT
        fn.first_name,
        ln.last_name,
        (1920 + FLOOR(RANDOM() * 105)) AS base_year,
        (FLOOR(RANDOM() * 12) + 1) AS base_month,
        (FLOOR(RANDOM() * 28) + 1) AS base_day,
        '555-' || LPAD((FLOOR(RANDOM() * 1000000))::TEXT, 6, '0') || LPAD(ROW_NUMBER() OVER ()::TEXT, 3, '0') AS phone_number,  -- Random and unique phone number generation
        LOWER(fn.first_name) || ROW_NUMBER() OVER () || '@example.com' AS email,
        ROW_NUMBER() OVER () AS row_num
    FROM first_names fn
    CROSS JOIN last_names ln
    ORDER BY RANDOM()
    LIMIT 100
),

final_users AS (
    SELECT
        first_name,
        last_name,
        (DATE(base_year || '-' || LPAD(base_month::TEXT, 2, '0') || '-' || LPAD(base_day::TEXT, 2, '0')) +
         (FLOOR(RANDOM() * 51 + 50) * INTERVAL '1 year') +
         (FLOOR(RANDOM() * 12) * INTERVAL '1 month') +
         (FLOOR(RANDOM() * 28) * INTERVAL '1 day'))::DATE AS date_of_birth,
        phone_number,
        email
    FROM generated_users
)

INSERT INTO users (first_name, last_name, date_of_birth, phone_number, email)
SELECT
    first_name,
    last_name,
    date_of_birth,
    phone_number,
    email
FROM final_users;

SELECT * FROM users;