package br.uece.eesdevops.amazingmovies;

import br.uece.eesdevops.amazingmovies.domain.entity.Movie;

public class FakeMovies {
	
	static Movie fakeMovieIfNoId() {
		Movie m = new Movie();
		m.setId(null);
		m.setAverageEvaluation(0.0);
		m.setCast("");
		m.setDirection("");
		m.setGenre("Romance");
		m.setName("Uma linda mulher");
		m.setProducer("Universal");
		m.setReleaseYear(1986);
		m.setSynopsis("Garotos fazem amizade e vivem aventuras na sua infância.");
		return m;
	}

	static Movie fakeMovie() {
		Movie m = new Movie();
		m.setId(new Integer(1));
		m.setAverageEvaluation(0.0);
		m.setCast("");
		m.setDirection("");
		m.setGenre("Romance");
		m.setName("Uma linda mulher");
		m.setProducer("Universal");
		m.setReleaseYear(1986);
		m.setSynopsis("Garotos fazem amizade e vivem aventuras na sua infância.");
		return m;
	}
	
}
