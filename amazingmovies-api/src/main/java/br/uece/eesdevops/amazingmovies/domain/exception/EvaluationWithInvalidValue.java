package br.uece.eesdevops.amazingmovies.domain.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EvaluationWithInvalidValue extends RuntimeException implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1103532234813519054L;

	public EvaluationWithInvalidValue(){
		super("Invalid value to evaluation!");
	}
}
