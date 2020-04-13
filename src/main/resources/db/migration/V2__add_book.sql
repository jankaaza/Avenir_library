create table books
(
    id       bigserial primary key,
    name     varchar,
    author   varchar,
    quantity int,
    category varchar
);