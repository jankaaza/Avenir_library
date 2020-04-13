CREATE TABLE roles(
    id bigserial NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

CREATE TABLE users(
    id bigserial NOT NULL,
    email varchar(40) NOT NULL,
    name varchar(40) NOT NULL,
    password varchar(100) NOT NULL,
    username varchar(15) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (role_id) REFERENCES roles(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
	);