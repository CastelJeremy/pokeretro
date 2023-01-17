CREATE DATABASE db_auth;
CREATE TABLE users (id number CONSTRAINT pk_users PRIMARY KEY, username varchar(64), password varchar(64));