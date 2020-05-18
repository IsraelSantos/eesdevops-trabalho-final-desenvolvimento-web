package br.uece.eesdevops.amazingmovies.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1934302338959958293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_m", nullable = false)
    private String name;

    @Column(name = "direction", nullable = false)
    private String direction;
    
    @Column(name = "genre", nullable = false)
    private String genre;
    
    @Column(name = "cast_m", nullable = false, columnDefinition="TEXT")
    private String cast;
    
    @Column(name = "synopsis", nullable = false, columnDefinition="TEXT")
    private String synopsis;
    
    @Column(name = "average_evaluation", nullable = false, columnDefinition="float(53) DEFAULT 0.0")
    private Double averageEvaluation = 0d;
    
    @Column(name = "release_year", nullable = false, columnDefinition="int4")
    private Integer releaseYear;
    
    @Column(name = "producer", nullable = true)
    private String producer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Double getAverageEvaluation() {
		return averageEvaluation;
	}

	public void setAverageEvaluation(Double averageEvaluation) {
		this.averageEvaluation = averageEvaluation;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

}
