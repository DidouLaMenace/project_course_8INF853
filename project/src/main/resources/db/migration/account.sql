CREATE DATABASE prixbanque;

USE prixbanque;

CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    balance DOUBLE NOT NULL
);
