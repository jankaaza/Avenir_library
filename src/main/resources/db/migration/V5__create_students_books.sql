CREATE TABLE students_books (
	student_id int8 NOT NULL,
	book_id int8 NOT NULL,
	PRIMARY KEY (student_id, book_id),
	FOREIGN KEY (student_id) REFERENCES students(id),
	FOREIGN KEY (book_id) REFERENCES books(id)
	);