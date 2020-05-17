package br.uece.eesdevops.amazingmovies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.service.ToEvaluateService;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@DisplayName("Runs all tests for domain service class responsible for evaluations")
public class ToEvaluateServiceTests {
	
	private final EvaluationRepository evaluationRepository =
            mock(EvaluationRepository.class);
	
	private final MovieRepository movieRepository = 
			mock(MovieRepository.class);

    private ToEvaluateService service;

    @BeforeEach
    void beforeEach() {
        service = new ToEvaluateService(this.evaluationRepository,
    			this.movieRepository);
    }

    @Test
    @DisplayName("should save evaluation and change the average of movie to returned status successfully")
    void should_save_evaluation_and_change_the_average_of_movie_to_returned_status_successfully() {
        Evaluation evaluation = FakeEvaluation.fakeEvaluationIfNoId();
        Movie movie = FakeMovies.fakeMovie();

        when(movieRepository.findById(evaluation.getMovie().getId()))
                .thenReturn(Optional.of(movie));

        when(evaluationRepository.save(evaluation))
                .thenReturn(evaluation);
        
        when(evaluationRepository.averageEvaluationByMovieId(evaluation.getMovie().getId()))
        		.thenReturn(evaluation.getValue());
        
        when(movieRepository.save(movie))
        		.thenReturn(movie);

        Evaluation tmp = service.execute(evaluation);

        assertEquals(evaluation.getId(), tmp.getId());
        assertEquals(evaluation.getValue(), tmp.getValue());

        verify(movieRepository).findById(evaluation.getMovie().getId());
        verify(evaluationRepository).save(evaluation);
    }
}
