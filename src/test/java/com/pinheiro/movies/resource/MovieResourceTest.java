package com.pinheiro.movies.resource;

import com.pinheiro.movies.annotation.ResourceTest;
import com.pinheiro.movies.resources.MoviesResource;
import com.pinheiro.movies.service.MovieService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@ResourceTest(MoviesResource.class)
public class MovieResourceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MovieService movieService;

  private static final String apiUrl = "/api/movies/";

  MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(new File("../example/example.csv")));

  public MovieResourceTest() throws IOException {
  }

  @Test
  public void save() throws Exception {

	this.mockMvc.perform(post(apiUrl).contentType(MediaType.MULTIPART_FORM_DATA)
										 .content(String.valueOf(multipartFile))).andDo(MockMvcResultHandlers.print())
					.andExpect(status().isOk());

	verify(this.movieService).save(any());
	verifyNoMoreInteractions(this.movieService);
  }
}
