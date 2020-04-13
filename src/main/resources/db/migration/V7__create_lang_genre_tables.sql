CREATE TABLE genres(
    id bigserial NOT NULL,
    name varchar(40) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO genres (name) VALUES ('Science');
INSERT INTO genres (name) VALUES ('Art');
INSERT INTO genres (name) VALUES ('Programming');
INSERT INTO genres (name) VALUES ('Fiction');

CREATE TABLE languages(
    id bigserial NOT NULL,
    name varchar(40) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO languages (name) VALUES ('English');
INSERT INTO languages (name) VALUES ('Russian');
INSERT INTO languages (name) VALUES ('Kyrgyz');