
package no.hiof.martr.com.movie;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import no.hiof.martr.com.movie.model.Movie;
import java.io.IOException;
import java.sql.SQLException;

public class MainJavaFX extends Application {

    private Stage primaryStage;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    public static MainJavaFX javaFXApplication;

    public MainJavaFX() {
        MainJavaFX.javaFXApplication = this;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        // laster alle filmer fra databasen
        try {
        MovieQuery query = new MovieQuery();
            for (Movie movie : query.loadMovies())
                movies.add(movie);
            mainView();
        } catch (SQLException e) {
            this.showAlert("Error connecting to database");
            System.out.println(e);
        }

    }

    public void mainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("view/mainWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene mainScene = new Scene(root, 900, 480);

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("FilmBasen");
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Something went  wrong" + e.toString());
        }
    }


    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showAlert(String alert) {
        Alert newAlert = new Alert(Alert.AlertType.ERROR);
        newAlert.setTitle("Error");
        newAlert.setHeaderText(null);
        newAlert.setContentText("Something went wrong!\n\n " + alert);

        newAlert.showAndWait();
    }
}
