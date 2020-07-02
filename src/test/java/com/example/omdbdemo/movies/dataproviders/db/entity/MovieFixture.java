package com.example.omdbdemo.movies.dataproviders.db.entity;

import com.example.omdbdemo.movies.core.model.Movie;

public class MovieFixture {
    public static String ALIEN_ID = "Alien_imdb_id";

    public static MovieEntity getAlienEntity() {
        return new MovieEntity()
                .withId(ALIEN_ID)
                .withTitle("Alien")
                .withYear("1979")
                .withRelease("22 Jun 1979")
                .withRuntime("117 min")
                .withGenre("Horror, Sci-Fi")
                .withDirector("Ridley Scott")
                .withWriter("Dan O'Bannon (screenplay by), Dan O'Bannon (story by), Ronald Shusett (story by)")
                .withActors("Tom Skerritt, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton")
                .withPlot("'After a space merchant vessel receives an unknown transmission as a distress call, one of " +
                        "the crew is attacked by a mysterious life form and they soon realize that its life cycle has " +
                        "merely begun.'")
                .withLanguage("English")
                .withCountry("UK, USA")
                .withAwards("Won 1 Oscar. Another 16 wins & 21 nominations.");
    }

    public static Movie getAlien() {
        return new Movie()
                .withId(ALIEN_ID)
                .withTitle("Alien")
                .withYear("1979")
                .withRelease("22 Jun 1979")
                .withRuntime("117 min")
                .withGenre("Horror, Sci-Fi")
                .withDirector("Ridley Scott")
                .withWriter("Dan O'Bannon (screenplay by), Dan O'Bannon (story by), Ronald Shusett (story by)")
                .withActors("Tom Skerritt, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton")
                .withPlot("'After a space merchant vessel receives an unknown transmission as a distress call, one of " +
                        "the crew is attacked by a mysterious life form and they soon realize that its life cycle has " +
                        "merely begun.'")
                .withLanguage("English")
                .withCountry("UK, USA")
                .withAwards("Won 1 Oscar. Another 16 wins & 21 nominations.");
    }
}