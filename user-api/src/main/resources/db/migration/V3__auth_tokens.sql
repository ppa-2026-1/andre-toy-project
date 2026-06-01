CREATE TABLE auth_tokens (
    token VARCHAR(128) PRIMARY KEY,
    handle VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL
);
