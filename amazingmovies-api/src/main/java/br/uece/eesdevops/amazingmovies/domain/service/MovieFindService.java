package br.uece.eesdevops.amazingmovies.domain.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.domain.exception.NotFoundException;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class MovieFindService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6994764871588135397L;
	
	MovieRepository movieRepository;

	@Autowired
	MovieFindService(
			MovieRepository movieRepository 
	){
		this.movieRepository = movieRepository;
	}
	
	public Movie execute(Long id) {
		try {
			Optional<Movie> movie = movieRepository.findById(id);
	        if (!movie.isPresent()) {
	            throw new NotFoundException(Movie.class, id);
	        } else {
	            return movie.get();
	        }
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
	}
	
}
