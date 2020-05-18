package br.uece.eesdevops.amazingmovies.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;
import br.uece.eesdevops.amazingmovies.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.amazingmovies.domain.exception.NotFoundException;
import br.uece.eesdevops.amazingmovies.domain.service.ChangeMovieService;
import br.uece.eesdevops.amazingmovies.repository.EvaluationRepository;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;
import br.uece.eesdevops.amazingmovies.web.entity.MovieDTO;

@RestController
@RequestMapping("/v1.0/movies")
public class MovieController implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4982526632752626588L;
	
	private MovieRepository movieRepository;
	private EvaluationRepository evaluationRepository;
	private ChangeMovieService movieSaveService;
	
	@Autowired
	MovieController(
				MovieRepository movieRepository,
				EvaluationRepository evaluationRepository,
				ChangeMovieService movieSaveService
			){
		this.movieRepository = movieRepository;
		this.evaluationRepository = evaluationRepository;
		this.movieSaveService = movieSaveService;
	}
    
	//Para setar página e comprimento na chamada: http://localhost:8081/movies/list?page=0&size=5
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Movie>> getAll(Pageable pageable) {
    	try {

			Page<Movie> movies = movieRepository.findAll(pageable);
			
			return ResponseEntity.ok(movies);

		} catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
    	try {
			Optional<Movie> movie = movieRepository.findById(id);
	        if (!movie.isPresent()) {
	            throw new NotFoundException(Movie.class, id);
	        } else {
	        	return ResponseEntity.ok(movie.get());
	        }
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(Movie.class,
					e.getMessage()); 
		}
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> save(@RequestBody MovieDTO movie) {
    	Movie entity = movie.toDomain();
        Movie res = movieSaveService.execute(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    
    @PutMapping(value = "/{id}", 
    		consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> change(@PathVariable Integer id, @RequestBody MovieDTO movie) {
    	Movie entity = movie.toDomain();
    	entity.setId(id);
        Movie res = movieSaveService.execute(entity);
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Movie> movie = movieRepository.findById(id);
		
		if(movie.isPresent()) {
			Movie tmp = movie.get();
			evaluationRepository.deleteByMovieId(tmp.getId());
			movieRepository.delete(tmp);
		}else {
			throw new NotFoundException(Movie.class, "The movie could not be deleted because it does not exist!");
		}
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
