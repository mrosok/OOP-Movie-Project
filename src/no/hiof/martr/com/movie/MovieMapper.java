package no.hiof.martr.com.movie;

import no.hiof.martr.com.movie.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieMapper {

    private Connection connection;

    public MovieMapper(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM movie;")
        )
        {
            while (resultSet.next()) {
                movieList.add(new Movie(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getString("releaseDate")),
                        resultSet.getInt("runtime"),
                        "",
                        resultSet.getString("posterURL")));
            }
        } catch(SQLException sqle) {
            MainJavaFX.javaFXApplication.showAlert("Error loading from database! \n\n" + sqle);;
        }
        return movieList;
    }

    public void writeMovietoDatabase(Movie movie) {
        String sql = String.format("INSERT INTO movie (title, description, releaseDate, runtime, genre) " +
                                    "VALUES('%s', '%s', '%s', '%d', '%s');",
                                    movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getRuntime(), movie.getGenre());

        try (Statement statement = connection.createStatement())

        {
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                movie.setId(resultSet.getInt(1));
        }

        catch (SQLException sqle) {
            MainJavaFX.javaFXApplication.showAlert("Error writing to database. Movie data will not persist on closing application");
        }
    }

    public void updateMovieInDatabase(Movie movie) {

        String sql = String.format("UPDATE movie " +
                        "SET title = '%s', description = '%s', releaseDate = '%s', runtime = '%d', genre = '%s' " +
                        "WHERE id = %d;",
                movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getRuntime(), movie.getGenre(), movie.getId());

        try (
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
        }
        catch(SQLException sqle)
        {
            MainJavaFX.javaFXApplication.showAlert("Error writing to database. Movie data will not persist on closing application");
        }

    }

    public void deleteMovieFromDatabase(Movie movie) {
        String sql = String.format("DELETE FROM movie where id = %d;", movie.getId());

        try (
                Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
        }
        catch (SQLException sqle)
        {
            MainJavaFX.javaFXApplication.showAlert("Error writing to database. Movie will not be deleted.");
        }
    }
}
