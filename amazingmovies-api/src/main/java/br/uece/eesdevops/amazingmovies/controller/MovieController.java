package br.uece.eesdevops.amazingmovies.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.amazingmovies.entity.Movie;
import br.uece.eesdevops.amazingmovies.service.MovieFindService;
import br.uece.eesdevops.amazingmovies.service.MovieListService;
import br.uece.eesdevops.amazingmovies.service.MovieSaveService;

@RestController
@RequestMapping("/v1.0/movies")
public class MovieController implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4982526632752626588L;
	
    private MovieFindService movieFindService;
	private MovieListService movieListService;
	private MovieSaveService movieSaveService;
	
	@Autowired
	MovieController(
				MovieFindService movieFindService,
				MovieListService movieListService,
				MovieSaveService movieSaveService
			){
		this.movieFindService = movieFindService;
		this.movieListService = movieListService;
		this.movieSaveService = movieSaveService;
	}
    
	//Para setar página e comprimento na chamada: http://localhost:8081/movies/list?page=0&size=5
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Movie>> getAll(Pageable pageable) {
        Page<Movie> movies = movieListService.execute(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Movie movie = movieFindService.execute(id);
        return ResponseEntity.ok(movie);
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        Movie res = movieSaveService.execute(movie);
        return ResponseEntity.ok(res);
    }

}
