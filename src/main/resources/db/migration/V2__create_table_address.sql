CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE address(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    zipCode VARCHAR(25) NOT NULL,
    district VARCHAR(25),
    street VARCHAR(100) NOT NULL,
    state VARCHAR (25) NOT NULL,
    city VARCHAR (45) NOT NULL,
    complement VARCHAR(255),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
