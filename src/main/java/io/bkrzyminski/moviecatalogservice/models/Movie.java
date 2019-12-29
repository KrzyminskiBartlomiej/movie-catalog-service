package io.bkrzyminski.moviecatalogservice.models;

/**
 * Model class
 *
 * @author bkrzyminski
 */
public class Movie {

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public Movie() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String movieId;
    private String name;

}
