CREATE TABLE trainers (
	id uuid PRIMARY KEY,
	user_id uuid NOT NULL,
	name VARCHAR(16) NOT NULL,
	gender VARCHAR(8) NOT NULL,
	starter INT NOT NULL
);
