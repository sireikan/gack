CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    name text NOT NULL,
    email varchar(120) NOT NULL,
    password text NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
);
