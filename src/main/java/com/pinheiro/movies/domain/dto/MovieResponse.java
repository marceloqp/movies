package com.pinheiro.movies.domain.dto;

import java.util.List;
import lombok.Data;

@Data
public class MovieResponse {

  private List<MovieDTO> min;
  private List<MovieDTO> max;
}
