package no.hiof.martr.com.movie.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents a movie, holds information about about the genre, the id in the database and the poster URL.
 * All other information is extended from the Production class.
 *
 * @author Martin
 */

public class Movie extends Production implements Comparable<Movie>{
    private String genre;
    private int id;
    private String posterURL;
    private static ArrayList<Movie> movieList = new ArrayList<>();

    // Constructors


    /**
     * Constructor used for a movie without ID or posterURL. Used when creating a new movie in the GUI.
     * @param title
     * @param description
     * @param releaseDate
     * @param genre
     * @param runtime
     */
    public Movie(String title, String description, LocalDate releaseDate, String genre, int runtime) {
        super(title, description, releaseDate, runtime);
        this.genre = genre;
        movieList.add(this);
    }

    /**
     * Constructor for use in connection to the database. Contains all necessary parameters.
     * @param id
     * @param title
     * @param description
     * @param releaseDate
     * @param runtime
     * @param posterURL
     */
    public Movie(int id, String title, String description, LocalDate releaseDate, int runtime, String genre, String posterURL) {
        super(title, description, releaseDate, runtime);
        this.id = id;
        this.genre = genre;
        this.posterURL = posterURL;
        movieList.add(this);
    }

    @Override
    public int compareTo(Movie o) {
        return super.getTitle().compareTo(o.getTitle());

    }

    /**
     * Returns a sorted list based on the compareTo method. Only for testing purposes, not used in application.
     */
    public static ArrayList<Movie> getSortedList() {
        Collections.sort(movieList);
        return movieList;

    }

    // Getters and Setters
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    @Override
    public String toString() {
        return super.getTitle() + " (" + super.getReleaseDate().getYear() + ")";
    }
}
