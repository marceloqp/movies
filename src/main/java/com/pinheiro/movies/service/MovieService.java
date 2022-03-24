package com.pinheiro.movies.service;

import com.pinheiro.movies.domain.Movie;
import com.pinheiro.movies.domain.dto.MovieDTO;
import com.pinheiro.movies.domain.dto.MovieResponse;
import com.pinheiro.movies.exception.GenericRestException;
import com.pinheiro.movies.repository.MovieRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  public void save(MultipartFile csv) {
	if (csv.isEmpty()) {
	  throw new GenericRestException(HttpStatus.PRECONDITION_FAILED, "Arquivo CSV vazio!");
	}
	List<Movie> movies = new ArrayList<>();
	try {
	  BufferedReader reader = new BufferedReader(new InputStreamReader(csv.getInputStream()));
	  CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).build());
	  Iterable<CSVRecord> records = csvParser.getRecords();
	  for (CSVRecord record : records) {
		int year = Integer.getInteger(record.get("year"));
		String title = record.get("title");
		String studios = record.get("studios");
		String producers = record.get("producers");
		Boolean winner = Boolean.getBoolean(record.get("winner"));
		Movie movie = Movie.builder().year(year).title(title).studios(studios).producers(producers).winner(winner).build();
		movies.add(movie);
	  }
	  movieRepository.saveAll(movies);

	} catch (IOException e) {
	  throw new GenericRestException(HttpStatus.BAD_REQUEST, "Falha ao consultar os registros do arquivo CSV.");
	} catch (IllegalArgumentException e) {
	  throw new GenericRestException(HttpStatus.BAD_REQUEST, "Falha ao salvar os registros do arquivo CSV.");
	}
  }

  public MovieResponse find() {

	MovieResponse response = new MovieResponse();
	List<Tuple> minimos = movieRepository.findMin();
	List<Tuple> maximos = movieRepository.findMax();

	minimos.forEach(tuple -> {
	  final MovieDTO dto = new MovieDTO();
	  dto.setProducer(tuple.get("producer", String.class));
	  dto.setInterval(tuple.get("interval", Integer.class));
	  dto.setPreviousWin(tuple.get("previousWin", Integer.class));
	  dto.setFollowingWin(tuple.get("followingWin", Integer.class));

	  response.getMin().add(dto);
	});

	maximos.forEach(tuple -> {
	  final MovieDTO dto = new MovieDTO();
	  dto.setProducer(tuple.get("producer", String.class));
	  dto.setInterval(tuple.get("interval", Integer.class));
	  dto.setPreviousWin(tuple.get("previousWin", Integer.class));
	  dto.setFollowingWin(tuple.get("followingWin", Integer.class));

	  response.getMax().add(dto);
	});

	return response;
  }
}
