CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE email(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);