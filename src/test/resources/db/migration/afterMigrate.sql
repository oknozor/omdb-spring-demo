INSERT INTO movie (id, title, year, release, genre, director, writer, actors, plot, language, country, awards,
                   runtime)
VALUES ('Alien_imdb_id', 'Alien', '1979', '22 Jun 1979', 'Horror, Sci-Fi', 'Ridley Scott',
        'Dan O"Bannon (screenplay by), Dan O"Bannon (story by), Ronald Shusett (story by)',
        'Tom Skerritt, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton',
        'After a space merchant vessel receives an unknown transmission as a distress call, one of ' ||
        'the crew is attacked by a mysterious life form and they soon realize that its life cycle has ' ||
        'merely begun.',
        'English', 'UK, USA', 'Won 1 Oscar. Another 16 wins & 21 nominations.', '117 min')
on conflict do nothing;
