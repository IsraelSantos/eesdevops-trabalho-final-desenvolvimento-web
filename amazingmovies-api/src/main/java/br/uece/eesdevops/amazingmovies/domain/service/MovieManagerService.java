package br.uece.eesdevops.amazingmovies.domain.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class MovieManagerService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1835642354880479518L;
	
	MovieRepository movieRepository;
	EvaluationRepository evaluationRepository;

	@Autowired
	MovieManagerService(
			MovieRepository movieRepository, 
			EvaluationRepository evaluationRepository
	){
		this.movieRepository = movieRepository;
		this.evaluationRepository = evaluationRepository;
	}
	
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}
	
	public List<Movie> list(){
		List<Movie> res = movieRepository.findAll();
		return res;
	}
}
