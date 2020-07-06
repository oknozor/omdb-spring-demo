package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieRanking;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieRankingEntity;
import com.example.omdbdemo.movies.dataproviders.db.entity.mapper.MovieEntityMapper;
import com.example.omdbdemo.movies.dataproviders.db.entity.mapper.MovieRankingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MovieDatabaseProvider implements MovieProvider {
    private final MovieEntityMapper mapper;
    private final MovieRankingMapper rankingMapper;
    private final MovieEntityRepository repository;
    private final MovieRankingRepository rankingRepository;

    // See https://www.postgresql.org/docs/current/datatype-datetime.html
    // Quix fix for pgsql timestamp range, there might be a better solution
    private final LocalDateTime PGSQL_MIN_TIMESTAMP = LocalDateTime.of(-4712, 1, 1, 1, 0);
    private final LocalDateTime PGSQL_MAX_TIMESTAMP = LocalDateTime.of(294276, 1, 1, 1, 0);

    @Override
    public Optional<Movie> byId(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Movie createOrUpdate(Movie movie) {
        MovieEntity movieUpdate = mapper.fromDomain(movie);
        // Comment are reattached to the movie when need to update
        // Updating comments is donne via the /comments controller
        repository.findById(movie.getId()).ifPresent(entity -> movieUpdate.setComments(entity.getComments()));
        MovieEntity updatedMovie = repository.save(movieUpdate);

        return mapper.toDomain(updatedMovie);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Movie> getAll() {
        List<MovieEntity> all = repository.findAll();
        return mapper.toDomain(all);
    }

    @Override
    public List<MovieRanking> getRankings() {
        List<MovieRankingEntity> rankingEntities = rankingRepository.getRankings();
        return rankingMapper.toDomain(rankingEntities);
    }

    @Override
    public List<MovieRanking> getRankingsWithInterval(LocalDate from, LocalDate to) {

        LocalDateTime toTime = to != null ? to.atStartOfDay().plusDays(1) : PGSQL_MAX_TIMESTAMP;
        LocalDateTime fromTime = from != null ? from.atStartOfDay() : PGSQL_MIN_TIMESTAMP;
        List<MovieRankingEntity> rankingEntities = rankingRepository.getRankingsWithInterval(fromTime, toTime);

        return rankingMapper.toDomain(rankingEntities);
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
