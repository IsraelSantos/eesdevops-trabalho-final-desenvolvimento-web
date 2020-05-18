package br.uece.eesdevops.amazingmovies;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;
import br.uece.eesdevops.amazingmovies.util.FakeMovies;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;


@AutoConfigureMockMvc
@SpringBootTest(classes = AmazingmoviesApplication.class)
@DisplayName("Runs all tests for movie registration")
@AutoConfigureEmbeddedDatabase
public class MovieControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MovieRepository movieRepository;
    
    @BeforeEach
    void beforeEach() {
    	movieRepository.deleteAllInBatch();
    }
    
    // region GET /movies

    @Test
    @DisplayName("should get all movies with no results")
    void should_get_all_movies_with_no_results() throws Exception {
        mockMvc.perform(get("/v1.0/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
    
    @Test
    @DisplayName("should get all movies with one result")
    void should_get_all_movies_with_one_result() throws Exception {
        Movie movie = FakeMovies.fakeMovieIfNoId();

        movie = movieRepository.save(movie);
        
        mockMvc.perform(get("/v1.0/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(movie.getId())))
                .andExpect(jsonPath("$.content[0].name", is(movie.getName())))
                .andExpect(jsonPath("$.content[0].direction", is(movie.getDirection())))
                .andExpect(jsonPath("$.content[0].genre", is(movie.getGenre())))
                .andExpect(jsonPath("$.content[0].cast", is(movie.getCast())))
                .andExpect(jsonPath("$.content[0].synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.content[0].averageEvaluation", is(movie.getAverageEvaluation())))
                .andExpect(jsonPath("$.content[0].releaseYear", is(movie.getReleaseYear())))
                .andExpect(jsonPath("$.content[0].producer", is(movie.getProducer())));
    }
    
    @Test
    @DisplayName("should get a movie")
    void should_get_a_movie() throws Exception {
        Movie movie = FakeMovies.fakeMovieIfNoId();

        movie = movieRepository.save(movie);
        
        mockMvc.perform(get("/v1.0/movies/"+movie.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(movie.getId())))
                .andExpect(jsonPath("$.name", is(movie.getName())))
                .andExpect(jsonPath("$.direction", is(movie.getDirection())))
                .andExpect(jsonPath("$.genre", is(movie.getGenre())))
                .andExpect(jsonPath("$.cast", is(movie.getCast())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.averageEvaluation", is(movie.getAverageEvaluation())))
                .andExpect(jsonPath("$.releaseYear", is(movie.getReleaseYear())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())));
    }
    
    // endregion

    // region POST /movies

    @Test
    @DisplayName("should save a new movie successfully")
    void should_save_new_movie_successfully() throws Exception {
        Movie movie = mapper.readValue(BodyRequests.newMovieRequest(), Movie.class);

        MockHttpServletRequestBuilder request = post("/v1.0/movies")
                .content(BodyRequests.newMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(movie.getName())))
                .andExpect(jsonPath("$.direction", is(movie.getDirection())))
                .andExpect(jsonPath("$.genre", is(movie.getGenre())))
                .andExpect(jsonPath("$.cast", is(movie.getCast())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.averageEvaluation", is(movie.getAverageEvaluation())))
                .andExpect(jsonPath("$.releaseYear", is(movie.getReleaseYear())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())));
    }
    
    //endregion

    // region PUT /movies/{id}

    @Test
    @DisplayName("should update a movie successfully")
    void should_update_a_movie_successfully() throws Exception {
        Movie movie = mapper.readValue(BodyRequests.newMovieRequest(), Movie.class);

        movie = movieRepository.save(movie);
        
        Integer id = movie.getId();
        
        movie = mapper.readValue(BodyRequests.updateMovieRequest(), Movie.class);

        MockHttpServletRequestBuilder request = put("/v1.0/movies/" + id)
                .content(BodyRequests.updateMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(movie.getName())))
                .andExpect(jsonPath("$.direction", is(movie.getDirection())))
                .andExpect(jsonPath("$.genre", is(movie.getGenre())))
                .andExpect(jsonPath("$.cast", is(movie.getCast())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.averageEvaluation", is(movie.getAverageEvaluation())))
                .andExpect(jsonPath("$.releaseYear", is(movie.getReleaseYear())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())));
    }
    
  //endregion
    
}
