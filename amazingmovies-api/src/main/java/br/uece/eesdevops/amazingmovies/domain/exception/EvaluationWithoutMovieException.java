package br.uece.eesdevops.amazingmovies.domain.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EvaluationWithoutMovieException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8289283196132608532L;

	public EvaluationWithoutMovieException(){
		super("You can't evaluation to a movie when it does not exist!");
	}
}
