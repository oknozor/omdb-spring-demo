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

INSERT INTO movie (id, title, year, release, genre, director, writer, actors, plot, language, country, awards,
                   runtime)
VALUES ('tt0816692', 'Interstellar', '2014', '07 Nov 2014', 'Adventure, Drama, Sci-Fi, Thriller', 'Christopher Nolan',
        'Jonathan Nolan, Christopher Nolan',
        'Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow',
        'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.',
        'English', 'UK, USA', 'Won 1 Oscar. Another 43 wins & 147 nominations.', '167 min')
on conflict do nothing;

INSERT INTO comment(id, movie_id, body, created) VALUES (1, 'tt0078748', 'Cannot wait for the sequel', current_timestamp)
on conflict do nothing;
INSERT INTO comment(id, movie_id, body, created) VALUES (2, 'tt0816692', 'An old comment', timestamp '2011-05-16 15:36:38')
on conflict do nothing;

