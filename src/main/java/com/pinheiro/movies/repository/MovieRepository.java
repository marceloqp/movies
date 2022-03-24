package com.pinheiro.movies.repository;

import com.pinheiro.movies.domain.Movie;
import java.util.List;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

  @Query(value = "SELECT"
			 + "  m.producers as producer,"
			 + "  maximo - minimo as interval,"
			 + "  minimo AS previousWin,"
			 + "  maximo AS followingWin"
			 + " FROM ("
			 + "	SELECT"
			 + "		m.producers,"
			 + "		(SELECT year  FROM movies  WHERE year = (select MIN(year ) from movies mv WHERE mv.producers = m.producers   )  LIMIT 1) AS minimo"
			 + "		(SELECT year  FROM movies  WHERE year = (select MAX(year ) from movies mv WHERE mv.producers = m.producers   )  LIMIT 1) AS maximo"
			 + "	FROM movies m"
			 + "	WHERE m.winner=1"
			 + " )"
			 + " WHERE interval > 0"
			 + " ORDER BY interval ASC LIMIT 1" , nativeQuery = true)
  List<Tuple> findMin();

  @Query(value = "SELECT"
			 + "  m.producers as producer,"
			 + "  maximo - minimo as interval,"
			 + "  minimo AS previousWin,"
			 + "  maximo AS followingWin"
			 + " FROM ("
			 + "	SELECT"
			 + "		m.producers,"
			 + "		(SELECT year  FROM movies  WHERE year = (select MIN(year ) from movies mv WHERE mv.producers = m.producers   )  LIMIT 1) AS minimo"
			 + "		(SELECT year  FROM movies  WHERE year = (select MAX(year ) from movies mv WHERE mv.producers = m.producers   )  LIMIT 1) AS maximo"
			 + "	FROM movies m"
			 + "	WHERE m.winner=1"
			 + " )"
			 + " WHERE interval > 0"
			 + " ORDER BY interval DESC LIMIT 1", nativeQuery = true)
  List<Tuple> findMax();
}