package com.pinheiro.movies.resources;

import com.pinheiro.movies.domain.dto.MovieDTO;
import com.pinheiro.movies.service.MovieService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class MoviesResource {

  private final MovieService movieService;

  @GetMapping
  public ResponseEntity<MovieDTO> find() {

	return ResponseEntity.ok(this.movieService.find());
  }

  @PostMapping
  @ResponseStatus(ACCEPTED)
  public void save(@RequestBody MultipartFile csv) {
	this.movieService.save(csv);
  }

}
