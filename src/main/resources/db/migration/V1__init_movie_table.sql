CREATE TABLE movie
(
    id       VARCHAR(100) primary key,
    title    VARCHAR(100),
    year     VARCHAR(5),
    runtime  VARCHAR(20),
    release  VARCHAR(100),
    genre    VARCHAR(100),
    director VARCHAR(100),
    writer   VARCHAR(100),
    actors   VARCHAR(100),
    plot     text,
    language VARCHAR(100),
    country  VARCHAR(100),
    awards   VARCHAR(100)
);

