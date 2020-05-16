package br.uece.eesdevops.amazingmovies.domain.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1244295872362184462L;

	public InternalServerErrorException(Class<?> c, String massage) {
		super("Error management of object type " + c.getName() + ". Massage: "+massage);
	}
}
