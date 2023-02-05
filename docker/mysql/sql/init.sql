CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT,
                      name text NOT NULL,
                      email varchar(120) NOT NULL,
                      password text NOT NULL,
                      PRIMARY KEY (id),
                      UNIQUE KEY (email)
);

insert into user (id, name, email, password) values
    (null, 'test', 'test@example.com', 'test')
;
