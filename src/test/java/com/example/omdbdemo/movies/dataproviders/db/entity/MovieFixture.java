package com.example.omdbdemo.movies.dataproviders.db.entity;

import java.util.Set;

public class MovieFixture {
    public static MovieEntity getAlien() {
        return new MovieEntity()
                .withTitle("Alien")
                .withYear("1979")
                .withRated("R")
                .withRelease("22 Jun 1979")
                .withRuntime("117 min")
                .withGenre("Horror, Sci-Fi")
                .withDirector("Ridley Scott")
                .withWriter("Dan O'Bannon (screenplay by), Dan O'Bannon (story by), Ronald Shusett (story by)")
                .withActors("Tom Skerritt, Sigourney Weaver, Veronica Cartwright, Harry Dean Stanton")
                .withPlot("After a space merchant vessel receives an unknown transmission as a distress call, one of the crew is attacked by a mysterious life form and they soon realize that its life cycle has merely begun.")
                .withLanguage("English")
                .withCountry("UK, USA")
                .withAwards("Won 1 Oscar. Another 16 wins & 21 nominations.")
                .withPoster("https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg")
                .withRatings(getAlienRatings())
                .withMetascore("89")
                .withImdbRating("8.4")
                .withImdbVotes("759,818")
                .withImdbId("tt0078748")
                .withType("movie")
                .withDvd("06 Jan 2004")
                .withBoxOffice("N/A")
                .withProduction("20th Century Fox")
                .withWebsite("N/A")
                .withResponse("True");
    }

    private static Set<RatingEntity> getAlienRatings() {
        return Set.of(
                new RatingEntity()
                        .withId(new RatingId(1L, "Internet Movie Database"))
                        .withValue("8.4/10"),
                new RatingEntity()
                        .withId(new RatingId(1L, "Rotten Tomatoes"))
                        .withValue("98%"),
                new RatingEntity()
                        .withId(new RatingId(1L, "Metacritic"))
                        .withValue("89/100")
        );
    }
}