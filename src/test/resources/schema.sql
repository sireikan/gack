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

CREATE TABLE IF NOT EXISTS gacha_info(
    id BIGINT UNSIGNED NOT NULL,
    gacha_id BIGINT UNSIGNED NOT NULL,
    gacha_name TEXT NOT NULL,
    banner_image TEXT NOT NULL,
    exec_count INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (gacha_id)
);

DELETE FROM gacha_info;

CREATE TABLE IF NOT EXISTS gacha_info_log(
    id BIGINT AUTO_INCREMENT,
    gacha_id BIGINT UNSIGNED NOT NULL,
    gacha_name TEXT NOT NULL,
    banner_image TEXT NOT NULL,
    exec_count INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    deleted datetime,
    PRIMARY KEY (id),
    INDEX gacha_info_log_idx_1 (gacha_id)
);


DELETE FROM gacha_info_log;

CREATE TABLE IF NOT EXISTS gacha_cost(
    id BIGINT UNSIGNED NOT NULL,
    gacha_id BIGINT UNSIGNED NOT NULL,
    cost_type INT UNSIGNED NOT NULL,
    cost INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (id),
    INDEX gacha_cost_idx_1 (gacha_id)
);

DELETE FROM gacha_cost;

CREATE TABLE IF NOT EXISTS gacha_cost_log(
    id BIGINT AUTO_INCREMENT,
    gacha_id BIGINT UNSIGNED NOT NULL,
     cost_type INT UNSIGNED NOT NULL,
    cost INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    deleted datetime,
    PRIMARY KEY (id),
    INDEX gacha_cost_log_idx_1 (gacha_id)
);

DELETE FROM gacha_cost_log;

CREATE TABLE IF NOT EXISTS gacha_probability(
    id BIGINT UNSIGNED NOT NULL,
    gacha_id BIGINT UNSIGNED NOT NULL,
    probability INT UNSIGNED NOT NULL,
    object_type INT UNSIGNED NOT NULL,
    object_id BIGINT UNSIGNED NOT NULL,
    object_count INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (id),
    INDEX gacha_probability_idx_1 (gacha_id)
);

DELETE FROM gacha_probability;

CREATE TABLE IF NOT EXISTS gacha_probability_log(
    id BIGINT AUTO_INCREMENT,
    gacha_id BIGINT UNSIGNED NOT NULL,
    probability INT UNSIGNED NOT NULL,
    object_type INT UNSIGNED NOT NULL,
    object_id BIGINT UNSIGNED NOT NULL,
    object_count INT UNSIGNED NOT NULL,
    created datetime NOT NULL,
    deleted datetime,
    PRIMARY KEY (id),
    INDEX gacha_probability_log_idx_1 (gacha_id)
);

DELETE FROM gacha_probability_log;
