package com.pinheiro.movies.repository;

import com.pinheiro.movies.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}