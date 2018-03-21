package no.hiof.martr.com.movie.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Movie extends Production implements Comparable<Movie>{
    private String genre;
    private int id;
    private static ArrayList<Movie> movieList = new ArrayList<>();

    // Constructors

    public Movie(String title, String description, LocalDate releaseDate, String genre, int runtime, boolean addToList) {
        super(title, description, releaseDate, runtime);
        this.genre = genre;
        if(addToList)
            movieList.add(this);
    }

    public Movie(String title, String description, LocalDate releaseDate, String genre, int runtime) {
        super(title, description, releaseDate, runtime);
        this.genre = genre;
        movieList.add(this);
    }

    @Override
    public int compareTo(Movie o) {
        return super.getTitle().compareTo(o.getTitle());

    }

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

    @Override
    public String toString() {
        return super.getTitle() + " (" + super.getReleaseDate().getYear() + ")";
    }
}
