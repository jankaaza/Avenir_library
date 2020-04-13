create table students
(
    id            bigserial primary key,
    first_name    varchar,
    last_name     varchar,
    university_id varchar unique
);