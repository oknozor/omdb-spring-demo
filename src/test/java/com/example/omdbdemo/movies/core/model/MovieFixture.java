package com.example.omdbdemo.movies.core.model;

import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;

import java.util.List;

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
                .withAwards("Won 1 Oscar. Another 16 wins & 21 nominations.")
                .withComments(List.of(CommentFixture.getCommentForAlien()));
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
                .withComments(List.of(CommentFixture.getCommentForMononoke()));
    }

    public static List<MovieRanking> rankings() {
        return List.of(
                MovieRanking.builder()
                        .rank(1)
                        .totalComments(12)
                        .movieId(MONONOKE_ID)
                        .build(),
                MovieRanking.builder()
                        .rank(2)
                        .totalComments(5)
                        .movieId(ALIEN_ID)
                        .build()
        );
    }
}