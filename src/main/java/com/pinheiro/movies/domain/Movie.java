package com.pinheiro.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "movies")
@ToString
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @Column(nullable = false)
    private Long id;
    @Column
    private int year;
    @Column
    private String title;
    @Column
    private String studios;
    @Column
    private String producers;
    @Column
    private Boolean winner;

}
