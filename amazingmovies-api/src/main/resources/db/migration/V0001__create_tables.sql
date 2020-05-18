CREATE SEQUENCE hibernate_sequence INCREMENT 1;


CREATE TABLE movie (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	average_evaluation float(53) NOT NULL DEFAULT 0.0,
	cast_m varchar(255) NOT NULL,
	direction varchar(255) NOT NULL,
	genre varchar(255) NOT NULL,
	name_m varchar(255) NOT NULL,
	producer varchar(255) NULL,
	release_year int NOT NULL,
	synopsis varchar(255) NOT NULL
);


CREATE TABLE evaluation (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	value_e float(53) NOT NULL,
	date_registration date not null,
	movie_id int NULL REFERENCES movie(id)
);

