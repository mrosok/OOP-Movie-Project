/* OBLIG 4
*
* Første bit av oppgaven, med compareTo/sortering av lister er testet i Main.
*
* Jeg testet ut litt styling helt på tampen før leveringsfrist. Rakk ikke å gjøre dialogboksen ferdig.
*
* */

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
        try {
            for (Movie movie : MovieData.loadMoviesFromDatabase())
                movies.add(movie);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mainView();
    }

    public void mainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("view/mainWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene mainScene = new Scene(root, 800, 480);

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
