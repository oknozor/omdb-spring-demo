package com.example.omdbdemo.movies.dataproviders.db.entity;

import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;

import java.util.List;
import java.util.Set;

public class MovieFixture {
    public static String ALIEN_ID = "tt0078748";
    public static String MONONOKE_ID = "tt0119698";

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

    public static UpdateMovieCommand updateAlienWithGerardDePardieu() {
        return new UpdateMovieCommand()
                .withTitle("Alien")
                .withYear("1979")
                .withRelease("22 Jun 1979")
                .withRuntime("117 min")
                .withGenre("Horror, Sci-Fi")
                .withDirector("Ridley Scott")
                .withWriter("Dan O'Bannon (screenplay by), Dan O'Bannon (story by), Ronald Shusett (story by)")
                .withActors("Gérard DePardieu, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton")
                .withPlot("'After a space merchant vessel receives an unknown transmission as a distress call, one of " +
                        "the crew is attacked by a mysterious life form and they soon realize that its life cycle has " +
                        "merely begun.'")
                .withLanguage("English")
                .withCountry("UK, USA")
                .withAwards("Won 1 Oscar. Another 16 wins & 21 nominations.");
    }

    public static Movie getPrincessMononoke() {
        return new Movie()
                .withId(MONONOKE_ID)
                .withTitle("Mononoke Hime")
                .withRelease("1994")
                .withLanguage("Japanese")
                .withRuntime("2H")
                .withWriter("Miyazaki")
                .withPlot("Prince Ashitaka on a journey to heal is cursed wound")
                .withGenre("Japanese Animation")
                .withCountry("Japan")
                .withDirector("Ayaho Miyazaki")
                .withActors("Brad Pit")
                .withAwards("Best animation movie all of time")
                .withComments(Set.of(CommentFixture.getCommentForMononoke()));
    }

    public static MovieEntity getPrincessMononokeEntity() {
        return new MovieEntity()
                .withId(MONONOKE_ID)
                .withTitle("Mononoke Hime")
                .withRelease("1994")
                .withLanguage("Japanese")
                .withRuntime("2H")
                .withWriter("Miyazaki")
                .withPlot("Prince Ashitaka on a journey to heal is cursed wound")
                .withGenre("Japanese Animation")
                .withCountry("Japan")
                .withDirector("Ayaho Miyazaki")
                .withActors("Brad Pit")
                .withAwards("Best animation movie all of time")
                .withComments(Set.of(CommentFixture.getCommentEntityForMononoke()));
    }
}