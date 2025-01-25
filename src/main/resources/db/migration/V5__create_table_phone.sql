CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE phone(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    phone VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL,
    user_id UUID,
    CONSTRAINT check_type CHECK (type IN ('comercial', 'residencial', 'celular')),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);