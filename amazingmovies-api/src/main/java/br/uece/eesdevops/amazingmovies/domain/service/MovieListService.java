package br.uece.eesdevops.amazingmovies.domain.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class MovieListService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7302482972333087217L;
	
	MovieRepository movieRepository;

	@Autowired
	MovieListService(
			MovieRepository movieRepository 
	){
		this.movieRepository = movieRepository;
	}
	
	public Page<Movie> execute(Pageable pageable) throws RuntimeException {

		try {

			Page<Movie> movies = movieRepository.findAll(pageable);
			
			return movies;

		} catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
	}
	
}
