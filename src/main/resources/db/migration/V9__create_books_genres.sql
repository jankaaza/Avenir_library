CREATE TABLE books_genres (
	book_id int8 NOT NULL,
	genre_id int8 NOT NULL,
	PRIMARY KEY (book_id, genre_id),
	FOREIGN KEY (book_id) REFERENCES books(id),
	FOREIGN KEY (genre_id) REFERENCES genres(id)
	);