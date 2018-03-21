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
import javafx.stage.Stage;
import no.hiof.martr.com.movie.model.Movie;
import java.io.IOException;
import java.time.LocalDate;

public class MainJavaFX extends Application {

    private Stage primaryStage;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    public static MainJavaFX javaFXApplication;

    public MainJavaFX() {
        movies.add(new Movie("A Shot in the Dark",
                "Inspector Jacques Clouseau investigates the murder of Mr. Benjamin Ballon's driver at a country estate.",
                LocalDate.of(1964,6,23), "comedy, mystery", 102));
        movies.add(new Movie("A Clockwork Orange",
                "In the future, a sadistic gang leader is imprisoned and volunteers for a conduct-aversion experiment, but it doesn't go as planned.",
                LocalDate.of(1971,12,29),"crime, drama, sci-fi", 136));
        movies.add(new Movie("Inception",
                "A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO.",
                LocalDate.of(2010,6,16), "action, adventure, sci-fi", 148));
        movies.add(new Movie("Goodfellas",
                "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.",
                LocalDate.of(1990,9,21), "biography, crime, drama",146));
        movies.add(new Movie("The Silence of the Lambs",
                "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.",
                LocalDate.of(1991,2,14),"crime, drama, thriller",118));
        movies.add(new Movie("Cowboy Bebop: the Movie",
                "A terrorist explosion releases a deadly virus on the masses, and it's up the bounty-hunting Bebop crew to catch the cold-blooded culprit.",
                LocalDate.of(2001,9,1), "animation, action, crime", 115));

        MainJavaFX.javaFXApplication = this;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        mainView();
    }

    public void mainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("view/mainWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene mainScene = new Scene(root, 640, 480);

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("FilmBasen");
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Something went  wrong");
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
}
