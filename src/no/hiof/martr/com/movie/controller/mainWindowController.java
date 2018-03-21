package no.hiof.martr.com.movie.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import no.hiof.martr.com.movie.MainJavaFX;
import no.hiof.martr.com.movie.model.Movie;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class mainWindowController {

    @FXML
    private BorderPane mainView;
    @FXML
    private ListView<Movie> movieListView;
    @FXML
    private Label lblMovieTitle;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblRuntime;
    @FXML
    private Label lblGenre;
    @FXML
    private Button btnEdit;

    private Movie currentMovie;

    @FXML
    public void initialize() {

        movieListView.setItems(MainJavaFX.javaFXApplication.getMovies().sorted());

        // eventListener på endring av listeelement
        movieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> observable, Movie oldValue, Movie newValue) {
                if (newValue != null) {
                    Movie movie = newValue;
                    showMovieDetails(movie);
                }
            }
        });

        // setter selection mode til single, og viser første i lista ved oppstart
        movieListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        movieListView.getSelectionModel().selectFirst();

        //lager onAction for Edit-knappen med anonym klasseimplementasjon
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showEditDialog();
            }
        });
    }

    //fyller vinduet med riktig tekst
    public void showMovieDetails(Movie movie) {
        lblMovieTitle.setText(movie.getTitle());
        txtDescription.setText(movie.getDescription());

        //bygger datofeltet med stringbuilder og datetimeformatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("Release date: ");
        sb.append(df.format(movie.getReleaseDate()) + "\n");
        lblDate.setText(sb.toString());

        lblRuntime.setText("Runtime: " + movie.getRuntime() + " minutes");
        lblGenre.setText("Genre: " + movie.getGenre());
    }

    @FXML
    public void showEditDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainView.getScene().getWindow());
        dialog.setTitle("Edit movie");
        dialog.setHeaderText("Edit movie information");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/DialogView.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Error loading dialog");
            e.printStackTrace();
        }
        currentMovie = movieListView.getSelectionModel().getSelectedItem();
        DialogController controller = loader.getController();
        controller.initialize(currentMovie);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // ... bruker trykket OK
            controller.processEditResults();
            movieListView.refresh();
            showMovieDetails(currentMovie);
        } else {
            // ... Bruker trykket CANCEL eller lukket dialogvinduet
        }
    }

    @FXML
    public void showNewDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainView.getScene().getWindow());
        dialog.setTitle("Add new movie");
        dialog.setHeaderText("Enter new movie information");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/DialogView.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Error loading dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // ... bruker trykket OK
            DialogController controller = loader.getController();
            Movie newMovie = controller.processNewResults();
            MainJavaFX.javaFXApplication.addMovie(newMovie);
            movieListView.getSelectionModel().select(newMovie);
        } else {
            // ... bruker trykket CANCEL eller lukket vinduet
        }
    }

    @FXML
    public void deleteMovie() {
        currentMovie = movieListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Movie");
        alert.setHeaderText(currentMovie.getTitle() + " will be deleted from list");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... bruker trykket OK
            Movie movie = movieListView.getSelectionModel().getSelectedItem();
            MainJavaFX.javaFXApplication.removeMovie(movie);
        } else {
            // ... bruker trykket CANCEL eller lukket vinduet
        }

    }
}