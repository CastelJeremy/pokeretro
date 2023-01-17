CREATE DATABASE db_auth;
CREATE TABLE users (id integer CONSTRAINT pk_users PRIMARY KEY, username varchar(64), password varchar(64));