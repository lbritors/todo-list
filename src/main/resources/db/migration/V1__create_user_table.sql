CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(75) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL DEFAULT false
);