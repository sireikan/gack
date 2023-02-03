CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT,
    name text NOT NULL,
    email varchar(120) NOT NULL,
    password text NOT NULL,
    roles text NOT NULL,
    enable_flag TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
);

insert into user (id, name, email, password, roles, enable_flag) values
    (null, 'test', 'test@example.com', 'test', 'ADMIN', true)
;
