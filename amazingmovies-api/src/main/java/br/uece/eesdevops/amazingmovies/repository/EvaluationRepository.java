package br.uece.eesdevops.amazingmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{

	@Modifying
	@Query("delete from Evaluation e where e.movie.id=:movieId")
	public void deleteByMovieId(@Param("movieId") Integer movieId);
	
}
