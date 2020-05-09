package br.uece.eesdevops.amazingmovies.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.amazingmovies.entity.Movie;
import br.uece.eesdevops.amazingmovies.repository.MovieRepository;
import br.uece.eesdevops.amazingmovies.service.MovieFindService;
import br.uece.eesdevops.amazingmovies.service.MovieListService;

@RestController
@RequestMapping("/movies")
public class MovieController implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4982526632752626588L;
	
    private MovieRepository repository;
    private MovieFindService movieFindService;
	private MovieListService movieListService;
	
	@Autowired
	MovieController(
				MovieFindService movieFindService,
				MovieListService movieListService,
				MovieRepository movieRepository
			){
		
	}

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = repository.findAll();
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping(value = "list", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Movie>> list(PageRequest pageRequest) {
        Page<Movie> movies = movieListService.execute(pageRequest);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Movie movie = movieFindService.execute(id);
        return ResponseEntity.ok(movie);
    }

}
