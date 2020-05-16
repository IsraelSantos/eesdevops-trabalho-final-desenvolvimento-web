package br.uece.eesdevops.amazingmovies.domain.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.ConflictMovieException;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class MovieSaveService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7302482972333087217L;
	
	MovieRepository movieRepository;

	@Autowired
	MovieSaveService(
			MovieRepository movieRepository 
	){
		this.movieRepository = movieRepository;
	}
	
	@Transactional
	public Movie execute(Movie movie) throws RuntimeException {

		try {
			
			Optional<List<Movie>> list;
			
			if (movie.getId() != null) {
				list = movieRepository.findByNameAndIdIsNot(movie.getName(), movie.getId());
			}else {
				list = movieRepository.findByName(movie.getName());
			}
			
			if (list.isPresent()) {
				throw new ConflictMovieException();
			}
			
			if (movie.getId() != null) {
				//Calcular a media de avaliações antes de salvar o filme
				
			}
			
			return movieRepository.save(movie);

		}catch (ConflictMovieException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
	}
	
}

