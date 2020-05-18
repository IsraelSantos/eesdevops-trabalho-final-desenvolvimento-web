package br.uece.eesdevops.amazingmovies.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import br.uece.eesdevops.amazingmovies.AmazingmoviesApplication;

public class BodyRequests {
	
    private static ClassLoader classLoader;

    static {
        classLoader = AmazingmoviesApplication.class.getClassLoader();
    }

    public static String newMovieRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-movie-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-movie-request.json could not be loaded.");
        }
    }

    public static String updateMovieRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-movie-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-movie-request.json could not be loaded.");
        }
    }
    
    public static String newEvaluationRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-evaluation-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-evaluation-request.json could not be loaded.");
        }
    }
}
