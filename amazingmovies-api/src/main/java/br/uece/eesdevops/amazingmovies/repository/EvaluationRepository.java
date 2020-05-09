package br.uece.eesdevops.amazingmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.amazingmovies.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{

}
