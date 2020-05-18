package br.uece.eesdevops.amazingmovies.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.amazingmovies.domain.entity.Evaluation;
import br.uece.eesdevops.amazingmovies.domain.service.ToEvaluateService;
import br.uece.eesdevops.amazingmovies.web.entity.EvaluationDTO;

@RestController
@RequestMapping("/v1.0/evaluations")
public class EvaluationController {

	ToEvaluateService toEvaluateService;
	
	@Autowired
	EvaluationController(ToEvaluateService toEvaluateService) {
		this.toEvaluateService = toEvaluateService;
	}
	
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EvaluationDTO> evaluate(@RequestBody EvaluationDTO evaluation) {
    	Evaluation ev = toEvaluateService.execute(evaluation.toDomain());
    	EvaluationDTO edto = evaluation.toDTO(ev);
        return ResponseEntity.status(HttpStatus.CREATED).body(edto);
    }
	
}
