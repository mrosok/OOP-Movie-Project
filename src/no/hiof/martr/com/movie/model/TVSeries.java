package no.hiof.martr.com.movie.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a TV Series. Contains information about the title, a description, the release year.
 * Contains a list of episodes in the series, the average runtime of the episodes and the number of seasons.
 *
 * @author Martin
 */

public class TVSeries implements Comparable<TVSeries> {
    private String title;
    private String description;
    private int releaseYear;
    private ArrayList<Episode> episodes = new ArrayList<>();
    private int avgRuntime;
    private int numberOfSeasons;
    private static ArrayList<TVSeries> tvSeriesList = new ArrayList<>();

    // Constructors

    /**
     * Default constructor. Contains parameters for all necessary fields. Sets the number of seasons to 1 upon initializing.
     * @param title the title of the TV-series
     * @param description a short description of the series
     * @param releaseYear the release year of the series. This should be the year of the first episode of the first season.
     */
    public TVSeries(String title, String description, int releaseYear) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.numberOfSeasons = 1;
        tvSeriesList.add(this);
    }

    // Methods

    /**
     * Adds a single episode to the list of episodes. Checks that the episode's season number is valid and updates
     * numberOfSeasons if necessary. Updates total average runtime.
     * @param episode the Episode object to be added
     */
    public void addEpisode(Episode episode) {

        if (episode.getSeason() > this.numberOfSeasons + 1) {
            System.out.println("Season number exceeds limit");
        } else if (episode.getSeason() == this.numberOfSeasons + 1) {
            this.numberOfSeasons++;
            this.episodes.add(episode);
        } else {
            this.episodes.add(episode);
        }

        updateAvgRuntime();
    }

    /**
     * Lists all episodes in a given season. Prints to System.out
     * @param season the season number of which episodes should be listed
     */
    public void listEpisodes(int season) {
        for (Episode episode : episodes) {
            if (episode.getSeason() == season) {
                System.out.println(episode + " " + episode.getReleaseDate());
            }
        }
    }

    /**
     * Creates placeholder episodes for testing purposes. Parameters include number of seasons and episodes to be created per season
     * Title will be "Episode n" incremented sequentially per season. Description will be "Default description".
     * Release date will be set to January 1st, 2000 and incremented daily per season
     *
     * @param seasons the number of seasons to be created
     * @param episodesPerSeason the number of episodes to be created per season
     */
    public void createEpisodes(int seasons, int episodesPerSeason) {
        for (int i = 1; i <= seasons; i++) {
            for (int j = 1; j <= episodesPerSeason; j++) {
                Episode episode = new Episode(j, i, "Episode " + j, "Default description", LocalDate.of(2000, 1, j + 1), randomRuntime());
                this.addEpisode(episode);
            }
        }
    }

    /**
     * updates average runtime of all episodes
     */
    public void updateAvgRuntime() {
        int sum = 0;
        for (Episode episode : episodes) {
            sum += episode.getRuntime();
        }
        this.avgRuntime = sum / episodes.size();
    }

    /**
     * returns a random number between 20 and 30
     * @return
     */
    public static int randomRuntime() {
        Random rand = new Random();
        int n = rand.nextInt(10) + 20;
        return n;
    }

    /**
     * returns an ArrayList of all the roles across all episodes of the series. May contain duplicates
     * @return
     */
    public ArrayList<Role> getCast() {
        ArrayList<Role> roles = new ArrayList<>();

        for (Episode episode : episodes)
            roles.addAll(episode.getRoleList());
        return roles;
    }

    /**
     * returns an ArrayList of all the roles across all episodes of the series. Ignores duplicates.
     * @return
     */
    public ArrayList<Role> getCastUnique() {
        // lagrer unike elementer i listen "result"
        ArrayList<Role> result = new ArrayList<>();

        for (Role role : getCast()) {
            // hvis result ikke innholder rollen, legg den til.
            if (!result.contains(role)) {
                result.add(role);
            }
        }
        return result;
    }


    /**
     * returns the number of episodes a role is featured in the series.
     * @param role the Role object to be queried
     * @return
     */
    private int numberOfEpisodes(Role role) {

        //Collections.frequency gir tilbake antall forekomster av et element i en liste
        int occurrences = Collections.frequency(getCast(), role);
        return occurrences;
    }

    /**
     * Prints all the unique roles in the series with information about the relevant actor and number of episodes featured.
     * Prints to System.out
     */
    public void getCastWithNumberOfEpisodes() {
        for (Role role : getCastUnique())
            System.out.println(role.getActor() + " plays " + role + " in " + numberOfEpisodes(role) + " episodes");
    }

    /**
     * returns a sorted list of TV Series.
     * @return
     */
    public static ArrayList<TVSeries> getSortedList() {
        Collections.sort(tvSeriesList);
        return tvSeriesList;

    }

    /**
     * returns a sorted list of Episodes in a series.
     * @return
     */
    public ArrayList<Episode> getSortedEpisodes() {
        Collections.sort(this.episodes);
        return this.episodes;

    }

    // Getters and Setters

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }

    public ArrayList<Episode> getEpisodes() {
        return this.episodes;
    }

    public int getAvgRuntime() {
        return avgRuntime;
    }

    public static ArrayList<TVSeries> getTvSeries() {
        return tvSeriesList;
    }

    @Override
    public String toString() {
        return this.title + "(" + this.releaseYear + ") / " + this.numberOfSeasons + (this.numberOfSeasons > 1 ? " seasons" : " season");
    }

    @Override
    public int compareTo(TVSeries o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
