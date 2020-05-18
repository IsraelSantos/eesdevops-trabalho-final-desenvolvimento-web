package br.uece.eesdevops.amazingmovies.web.entity;

import java.io.Serializable;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class EvaluationDTO implements DTO<Evaluation, EvaluationDTO>, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 240535357803291361L;

	private Double value;
    
    private Integer idMovie;
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
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

	@Override
	public EvaluationDTO toDTO(Evaluation entity) {
		EvaluationDTO res = new EvaluationDTO();
		res.setIdMovie(entity.getMovie().getId());
		res.setValue(entity.getValue());
		return res;
	}

}
