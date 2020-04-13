alter table book_instances
    add column user_id bigint references users;