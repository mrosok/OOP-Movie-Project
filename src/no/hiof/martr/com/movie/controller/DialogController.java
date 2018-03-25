package no.hiof.martr.com.movie.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hiof.martr.com.movie.MainJavaFX;
import no.hiof.martr.com.movie.MovieQuery;
import no.hiof.martr.com.movie.model.Movie;

import java.sql.SQLException;
import java.time.LocalDate;

public class DialogController {

    @FXML
    private DialogPane dialog;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtDescription;

    @FXML
    private DatePicker valDate;

    @FXML
    private TextField txtRuntime;

    @FXML
    private TextField txtGenre;

    private Movie currentMovie;

    @FXML
    public void initialize(Movie movie) {
        currentMovie = movie;
        txtTitle.setText(movie.getTitle());
        txtDescription.setText(movie.getDescription());
        valDate.setValue(movie.getReleaseDate());
        txtRuntime.setText("" + movie.getRuntime());
        txtGenre.setText(movie.getGenre());
    }

    public void processEditResults() {
        String title = txtTitle.getText().trim();
        String desc = txtDescription.getText().trim();
        LocalDate date = valDate.getValue();
        String runtime = txtRuntime.getText().trim();
        String genre = txtGenre.getText().trim();

        currentMovie.setTitle(title);
        currentMovie.setDescription(desc);
        currentMovie.setReleaseDate(date);
        currentMovie.setRuntime(Integer.parseInt(runtime));
        currentMovie.setGenre(genre);


        //lagrer til database
        try {
            MovieQuery query = new MovieQuery();
            query.updateMovie(currentMovie);
        } catch (SQLException e) {
            MainJavaFX.javaFXApplication.showAlert(e.toString());
        }
    }

    public Movie processNewResults() {
        String title = txtTitle.getText().trim();
        String desc = txtDescription.getText().trim();
        LocalDate date = valDate.getValue();
        int runtime = Integer.parseInt(txtRuntime.getText().trim());
        String genre = txtGenre.getText().trim();

        Movie newMovie = new Movie(title, desc, date, genre, runtime);

        //lagrer til database
        try {
            MovieQuery query = new MovieQuery();
            query.saveMovie(newMovie);

        } catch (SQLException e) {
            MainJavaFX.javaFXApplication.showAlert(e.toString());
        }
        return newMovie;
    }
}
