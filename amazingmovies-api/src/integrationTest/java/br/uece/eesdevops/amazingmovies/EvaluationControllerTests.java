package br.uece.eesdevops.amazingmovies;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;
import br.uece.eesdevops.amazingmovies.util.BodyRequests;
import br.uece.eesdevops.amazingmovies.web.entity.EvaluationDTO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@AutoConfigureMockMvc
@SpringBootTest(classes = AmazingmoviesApplication.class)
@DisplayName("Runs all tests for evaluation registration")
@AutoConfigureEmbeddedDatabase
public class EvaluationControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EvaluationRepository evaluationRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @BeforeEach
    void beforeEach() {
    	movieRepository.deleteAllInBatch();
    	evaluationRepository.deleteAllInBatch();
    }
    
 // region POST /evaluations

    @Test
    @DisplayName("should save a new evaluation to return successfully")
    void should_save_new_evaluations_to_return_successfully() throws Exception {
        EvaluationDTO evaluation = mapper.readValue(BodyRequests.newEvaluationRequest(), EvaluationDTO.class);
        
        Movie movie = mapper.readValue(BodyRequests.newMovieRequest(), Movie.class);
        
        movie = movieRepository.save(movie);
        
        evaluation.setIdMovie(movie.getId());
        
        String requestS = mapper.writeValueAsString(evaluation);
        
        MockHttpServletRequestBuilder request = post("/v1.0/evaluations")
                .content(requestS)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value", is(evaluation.getValue())))
                .andExpect(jsonPath("$.idMovie", is(evaluation.getIdMovie())));
        
        request = post("/v1.0/evaluations")
                .content(requestS)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value", is(evaluation.getValue())))
                .andExpect(jsonPath("$.idMovie", is(evaluation.getIdMovie())));
    }
    
    //endregion

}
