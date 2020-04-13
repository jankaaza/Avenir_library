drop table students_books;

delete from books;

alter table books
    drop column name,
    drop column quantity,
    drop column category,
    add column title varchar(200),
    add column summary varchar,
    add column isbn varchar(13),
    add column language_id bigint references languages;