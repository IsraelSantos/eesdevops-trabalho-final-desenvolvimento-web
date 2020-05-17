package br.uece.eesdevops.amazingmovies;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class FakeEvaluation {
	
	static Evaluation fakeEvaluationIfNoId() {
		Evaluation e = new Evaluation();
		e.setId(null);
		e.setValue(0.7);
		Movie movie = new Movie();
		movie.setId(1);
		e.setMovie(movie);
		return e;
	}

	static Evaluation fakeEvaluation() {
		Evaluation e = new Evaluation();
		e.setId(new Integer(1));
		e.setValue(0.7);
		return e;
	}
	
}
