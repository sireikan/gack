CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE IF NOT EXISTS user (
                      id BIGINT AUTO_INCREMENT,
                      name text NOT NULL,
                      email varchar(120) NOT NULL,
                      password text NOT NULL,
                      PRIMARY KEY (id),
                      UNIQUE KEY (email)
);

DELETE FROM user;
