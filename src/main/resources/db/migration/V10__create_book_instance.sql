CREATE TABLE book_instances (
    id uuid NOT NULL,
    due_back timestamp NULL,
    status varchar(60) NOT NULL,
    book_id int8 NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
    );