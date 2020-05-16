package br.uece.eesdevops.amazingmovies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	Optional<List<Movie>> findByNameAndIdIsNot(String name, Long id);
	
	Optional<List<Movie>> findByName(String name);
	
}
