CREATE TYPE role_enum AS ENUM ('USER', 'ADMIN');
CREATE TABLE IF NOT EXISTS user_tb (
    id SERIAL PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles role_enum NOT NULL,
    create_account TIMESTAMP WITHOUT TIME ZONE
);