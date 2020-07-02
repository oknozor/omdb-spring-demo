CREATE TABLE movie
(

    id         bigserial primary key,
    title      VARCHAR(100),
    year       VARCHAR(5),
    rated      VARCHAR(100),
    release    VARCHAR(100),
    genre      VARCHAR(100),
    director   VARCHAR(100),
    writer     text,
    actors     text,
    plot       text,
    language   VARCHAR(100),
    country    VARCHAR(100),
    awards     VARCHAR(100),
    poster     VARCHAR(255),
    metascore  VARCHAR(100),
    imdb_rating VARCHAR(100),
    imdb_votes  VARCHAR(100),
    imdb_id     VARCHAR(100),
    runtime     VARCHAR(100),
    type       VARCHAR(100),
    dvd        VARCHAR(100),
    box_office  VARCHAR(100),
    production VARCHAR(100),
    website    VARCHAR(100),
    response   VARCHAR(100)
);

CREATE TABLE movie_rating
(
    movie_id bigint,
    source   VARCHAR(100),
    value    VARCHAR(30),
    primary key (movie_id, source),
    foreign key (movie_id) references movie (id)
);
