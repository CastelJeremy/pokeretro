CREATE TABLE trainers (
	id		uuid 		NOT NULL,
	username	VARCHAR(50)	NOT NULL,
	gender		VARCHAR(50)	NOT NULL,
	CONSTRAINT trainer_pk PRIMARY KEY (id)
);
