package br.uece.eesdevops.amazingmovies.domain.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052530680010048609L;

	public NotFoundException(Class<?> c, Integer id) {
		super(c.getName()+" for ID " + id + " does not exist.");
	}
	
	public NotFoundException(Class<?> c, String massage) {
		super(c.getName()+": "+massage);
	}
}
