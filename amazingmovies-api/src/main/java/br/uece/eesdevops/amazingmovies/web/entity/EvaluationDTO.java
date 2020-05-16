package br.uece.eesdevops.amazingmovies.web.entity;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class EvaluationDTO implements DTO<Evaluation>{

    private Double value;
    
    private Long idMovie;	
	
	@Override
	public Evaluation toDomain() {
		Evaluation res = new Evaluation();
		res.setValue(this.value);
		Movie movie = new Movie();
		movie.setId(this.idMovie);
		res.setMovie(movie);
		return res;
	}

}
