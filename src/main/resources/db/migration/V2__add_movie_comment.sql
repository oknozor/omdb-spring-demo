CREATE TABLE IF NOT EXISTS comment
(
    id       bigserial primary key,
    movie_id VARCHAR(100) references movie (id),
    body     text not null,
    created  timestamp with time zone

);

alter sequence if exists comment_id_seq restart with 100;

