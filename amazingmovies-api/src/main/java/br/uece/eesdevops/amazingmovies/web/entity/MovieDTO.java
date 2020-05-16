package br.uece.eesdevops.amazingmovies.web.entity;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class MovieDTO implements DTO<Movie>{

    private String name;

    private String direction;
    
    private String genre;
    
    private String cast;
    
    private String synopsis;
    
    private Integer releaseYear;
    
    private String producer;	
	
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
