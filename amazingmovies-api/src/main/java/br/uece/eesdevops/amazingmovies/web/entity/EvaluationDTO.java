package br.uece.eesdevops.amazingmovies.web.entity;

import java.io.Serializable;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class EvaluationDTO implements DTO<Evaluation>, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 240535357803291361L;

	private Double value;
    
    private Long idMovie;	
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

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
