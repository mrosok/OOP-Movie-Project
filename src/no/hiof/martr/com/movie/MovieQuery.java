package no.hiof.martr.com.movie;

import no.hiof.martr.com.movie.model.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieQuery {

    private final static String URL = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    private Connection connection;
    private MovieMapper movieMapper;

    public MovieQuery() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MovieMapper movieMapper = new MovieMapper(connection);
        this.connection = connection;
        this.movieMapper = movieMapper;
    }

    public void saveMovie(Movie movie) {
        this.movieMapper.writeMovietoDatabase(movie);
    }

    public void deleteMovie(Movie movie) {
        this.movieMapper.deleteMovieFromDatabase(movie);
    }

    public void updateMovie(Movie movie) {
        this.movieMapper.updateMovieInDatabase(movie);
    }

    public ArrayList<Movie> loadMovies() {
       return this.movieMapper.getAllMovies();
    }

}
