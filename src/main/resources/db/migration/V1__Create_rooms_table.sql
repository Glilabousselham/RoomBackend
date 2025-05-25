-- File: V1__Create_rooms_table.sql
CREATE TABLE rooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(255),
    password VARCHAR(255),
    max_users INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
