package br.uece.eesdevops.amazingmovies.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;

@Service
public class ToEvaluateService {

	EvaluationRepository evaluationRepository;
	MovieRepository movieRepository;
	
	@Autowired
	ToEvaluateService(
			EvaluationRepository evaluationRepository
			){
		this.evaluationRepository = evaluationRepository;
	}
	
	public void execute(Evaluation evaluation) throws RuntimeException{
		
	}
	
}
