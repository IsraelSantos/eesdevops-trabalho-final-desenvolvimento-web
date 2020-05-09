package br.uece.eesdevops.amazingmovies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.entity.Movie;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class MovieManagerService {

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
