create table authors
(
    id         bigserial primary key,
    first_name varchar,
    last_name  varchar,
    birth_date timestamp,
    death_date timestamp,
    biography  text
);

alter table books
    drop column author;

alter table books
    add column author_id bigint references authors;