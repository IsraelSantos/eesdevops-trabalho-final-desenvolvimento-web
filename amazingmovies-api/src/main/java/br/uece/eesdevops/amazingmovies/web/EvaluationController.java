package br.uece.eesdevops.amazingmovies.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.amazingmovies.domain.service.ToEvaluateService;
import br.uece.eesdevops.amazingmovies.web.entity.EvaluationDTO;

@RestController
@RequestMapping("/v1.0/evaluation")
public class EvaluationController {

	ToEvaluateService toEvaluateService;
	
	@Autowired
	EvaluationController(ToEvaluateService toEvaluateService) {
		this.toEvaluateService = toEvaluateService;
	}
	
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EvaluationDTO> evaluate(@RequestBody EvaluationDTO movie) {

        return ResponseEntity.ok(movie);
    }
	
}
