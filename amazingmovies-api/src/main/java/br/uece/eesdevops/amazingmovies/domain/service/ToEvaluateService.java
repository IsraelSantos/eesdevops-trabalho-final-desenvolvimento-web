package br.uece.eesdevops.amazingmovies.domain.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.EvaluationWithInvalidValue;
import br.uece.eesdevops.amazingmovies.domain.exception.EvaluationWithoutMovieException;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.domain.exception.NotFoundException;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class ToEvaluateService {

	EvaluationRepository evaluationRepository;
	MovieRepository movieRepository;
	
	@Autowired
	public ToEvaluateService(
			EvaluationRepository evaluationRepository,
			MovieRepository movieRepository
			){
		this.evaluationRepository = evaluationRepository;
		this.movieRepository = movieRepository;
	}
	
	@Transactional
	public Evaluation execute(Evaluation evaluation) throws RuntimeException{
		try {
			
			if (evaluation.getMovie() == null || (evaluation.getMovie() != null && evaluation.getMovie().getId() == null)) {
				throw new EvaluationWithoutMovieException();
			}
			
			Optional<Movie> movie = movieRepository.findById(evaluation.getMovie().getId());
			
			if(movie.isPresent()) {
				if (evaluation.getValue() == null || (evaluation.getValue() != null && (evaluation.getValue()<0 || evaluation.getValue()>5))) {
					throw new EvaluationWithInvalidValue();
				}
				evaluation.setDateRegistration(Calendar.getInstance().getTime());
				
				Evaluation res = evaluationRepository.save(evaluation);
				Double average = evaluationRepository.averageEvaluationByMovieId(evaluation.getMovie().getId());
				Movie mTmp = movie.get();
				mTmp.setAverageEvaluation(average);
				movieRepository.save(mTmp);
				return res;
			}else {
				throw new EvaluationWithoutMovieException();
			}

		}catch(EvaluationWithInvalidValue e) {
			throw e;
		}catch(EvaluationWithoutMovieException e) {
			throw e;
		}catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
	}
	
}
