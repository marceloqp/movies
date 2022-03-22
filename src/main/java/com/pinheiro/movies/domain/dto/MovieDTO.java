package com.pinheiro.movies.domain.dto;

import lombok.Data;

@Data
public class MovieDTO {

  private String producer;
  private int interval;
  private int previousWin;
  private int followingWin;

}
