package br.uece.eesdevops.amazingmovies.web.entity;

import java.io.Serializable;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class MovieDTO implements DTO<Movie>, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5640361436173465668L;

	private String name;

    private String direction;
    
    private String genre;
    
    private String cast;
    
    private String synopsis;
    
    private Integer releaseYear;
    
    private String producer;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	@Override
	public Movie toDomain() {
		Movie res = new Movie();
		res.setName(this.name);
		res.setCast(this.cast);
		res.setDirection(this.direction);
		res.setGenre(this.genre);
		res.setProducer(this.producer);
		res.setReleaseYear(this.releaseYear);
		res.setSynopsis(this.synopsis);
		return res;
	}
}
