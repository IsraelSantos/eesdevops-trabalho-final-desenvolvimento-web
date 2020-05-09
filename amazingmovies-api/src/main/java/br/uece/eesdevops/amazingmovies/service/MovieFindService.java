package br.uece.eesdevops.amazingmovies.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.entity.Movie;
import br.uece.eesdevops.amazingmovies.exception.NotFoundException;
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
		Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new NotFoundException(Movie.class, id);
        } else {
            return movie.get();
        }
	}
	
}
