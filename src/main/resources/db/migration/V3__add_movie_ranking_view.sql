CREATE OR REPLACE VIEW movie_rank AS
SELECT movie_id, count(comment.id) AS comment_count, dense_rank() OVER (ORDER BY count(comment.id) DESC ) rank
FROM movie,
     comment
WHERE movie.id = comment.movie_id
GROUP BY movie_id;
