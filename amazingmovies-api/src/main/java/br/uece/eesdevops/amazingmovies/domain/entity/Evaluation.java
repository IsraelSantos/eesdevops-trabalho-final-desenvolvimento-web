package br.uece.eesdevops.amazingmovies.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 833771229942359098L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @Column(name = "value_e", nullable = false, columnDefinition="float(53)")
    private Double value = 0d;
    
	@Column(name = "date_registration", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date dateRegistration;
    
    @ManyToOne
    private Movie movie;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}
    
}
