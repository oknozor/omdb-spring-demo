INSERT INTO movie (id, title, year, release, genre, director, writer, actors, plot, language, country, awards,
                   runtime)
VALUES ('tt0078748', 'Alien', '1979', '22 Jun 1979', 'Horror, Sci-Fi', 'Ridley Scott',
        'Dan O''Bannon (screenplay by), Dan O''Bannon (story by), Ronald Shusett (story by)',
        'Tom Skerritt, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton',
        'After a space merchant vessel receives an unknown transmission as a distress call, one of ' ||
        'the crew is attacked by a mysterious life form and they soon realize that its life cycle has ' ||
        'merely begun.',
        'English', 'UK, USA', 'Won 1 Oscar. Another 16 wins & 21 nominations.', '117 min')
on conflict do nothing;

INSERT INTO comment(id, movie_id, body, created) VALUES (1, 'tt0078748', 'Cannot wait for the sequel', current_timestamp)
on conflict do nothing;
INSERT INTO comment(id, movie_id, body, created) VALUES (1, 'tt0078748', 'An old comment', timestamp '2019-07-05 00:00:00')
on conflict do nothing;

