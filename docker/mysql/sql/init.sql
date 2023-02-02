CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    roles text NOT NULL,
    enable_flag TYNYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
)
