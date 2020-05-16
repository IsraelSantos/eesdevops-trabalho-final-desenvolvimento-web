package br.uece.eesdevops.amazingmovies.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.domain.exception.NotFoundException;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class RemoveMovieService {
	
	MovieRepository movieRepository;
	EvaluationRepository evaluationRepository;
	
	@Autowired
	RemoveMovieService(MovieRepository movieRepository,
			EvaluationRepository evaluationRepository){
		this.movieRepository = movieRepository;
		this.evaluationRepository = evaluationRepository;
	}
	
	@Transactional
	public void execute(Long idMovie) throws RuntimeException {
		try {
			
			Optional<Movie> movie = movieRepository.findById(idMovie);
			
			if(movie.isPresent()) {
				Movie tmp = movie.get();
				evaluationRepository.deleteByMovieId(tmp.getId());
				movieRepository.delete(tmp);
			}else {
				throw new NotFoundException(Movie.class, "The movie could not be deleted because it does not exist!");
			}

		}catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
	}
}
