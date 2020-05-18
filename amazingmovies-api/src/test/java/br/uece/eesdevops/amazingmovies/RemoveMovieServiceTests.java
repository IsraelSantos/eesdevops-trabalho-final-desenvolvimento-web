package br.uece.eesdevops.amazingmovies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.ConflictMovieException;
import br.uece.eesdevops.amazingmovies.domain.exception.NotFoundException;
import br.uece.eesdevops.amazingmovies.domain.service.ChangeMovieService;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;
import br.uece.eesdevops.amazingmovies.util.FakeMovies;

@DisplayName("Runs all tests for domain service class responsible for remove a movie")
public class RemoveMovieServiceTests {
	
	private final MovieRepository movieRepository = 
			mock(MovieRepository.class);

    private ChangeMovieService service;

    @BeforeEach
    void beforeEach() {
        service = new ChangeMovieService(this.movieRepository);
    }

    @Test
    @DisplayName("should save a movie to returned status successfully")
    void should_save_a_movie_to_returned_status_successfully() {
        Movie movie = FakeMovies.fakeMovieIfNoId();
        
        when(movieRepository.findByName(movie.getName()))
        		.thenReturn(Optional.empty());
        
        when(movieRepository.save(movie))
        		.thenReturn(FakeMovies.fakeMovie());

        Movie tmp = service.execute(movie);

        assertNotEquals(null, tmp.getId());
        assertEquals(movie.getAverageEvaluation(), tmp.getAverageEvaluation());
        assertEquals(movie.getCast(), tmp.getCast());
        assertEquals(movie.getDirection(), tmp.getDirection());
        assertEquals(movie.getGenre(), tmp.getGenre());
        assertEquals(movie.getName(), tmp.getName());
        assertEquals(movie.getProducer(), tmp.getProducer());
        assertEquals(movie.getReleaseYear(), tmp.getReleaseYear());
        assertEquals(movie.getSynopsis(), tmp.getSynopsis());

        verify(movieRepository).findByName(movie.getName());
        verify(movieRepository).save(movie);
    }
    
    @Test
    @DisplayName("should return not found exception to returned status successfully")
    void should_return_not_found_exception_to_returned_status_successfully() {
        Movie movie = FakeMovies.fakeMovie();
        
        when(movieRepository.findById(movie.getId()))
        		.thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
        	service.execute(movie);
        });

        verify(movieRepository).findById(movie.getId());
    }
    
    @Test
    @DisplayName("should conflict movie exception to returned status successfully")
    void should_return_conflict_movie_exception_to_returned_status_successfully() {
        Movie movie = FakeMovies.fakeMovie();
        
        when(movieRepository.findById(movie.getId()))
        		.thenReturn(Optional.of(movie));
        
        when(movieRepository.findByNameAndIdIsNot(movie.getName(), movie.getId()))
        		.thenReturn(Optional.of(FakeMovies.fakeMovieList()));

        assertThrows(ConflictMovieException.class, () -> {
        	service.execute(movie);
        });

        verify(movieRepository).findById(movie.getId());
        verify(movieRepository).findByNameAndIdIsNot(movie.getName(), movie.getId());
    }
    

}