package no.hiof.martr.com.movie.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TVSeries implements Comparable<TVSeries> {
    private String title;
    private String description;
    private int releaseYear;
    private ArrayList<Episode> episodes = new ArrayList<>();
    private int avgRuntime;
    private int numberOfSeasons;
    private static ArrayList<TVSeries> tvSeriesList = new ArrayList<>();

    // Constructors

    public TVSeries(String title, String description, int releaseYear) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.numberOfSeasons = 1;
        tvSeriesList.add(this);
    }

    // Methods

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

    public void listEpisodes(int season) {
        for (Episode episode : episodes) {
            if (episode.getSeason() == season) {
                System.out.println(episode + " " + episode.getReleaseDate());
            }
        }
    }

    public void createEpisodes(int seasons, int episodesPerSeason) {
        for (int i = 1; i <= seasons; i++) {
            for (int j = 1; j <= episodesPerSeason; j++) {
                Episode episode = new Episode(j, i, "Episode " + j, "Default description", LocalDate.of(2000, 1, j + 1), randomRuntime());
                this.addEpisode(episode);
            }
        }
    }

    // Oppdaterer avgRuntime. Kalt hver gang en episode legges til
    public void updateAvgRuntime() {
        int sum = 0;
        for (Episode episode : episodes) {
            sum += episode.getRuntime();
        }
        this.avgRuntime = sum / episodes.size();
    }

    // returnerer et tilfeldig heltall mellom 20 og 30
    public static int randomRuntime() {
        Random rand = new Random();
        int n = rand.nextInt(10) + 20;
        return n;
    }

    public ArrayList<Role> getCast() {
        ArrayList<Role> roles = new ArrayList<>();

        for (Episode episode : episodes)
            roles.addAll(episode.getRoleList());
        return roles;
    }

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

    //returnerer antallet episoder en rolle er med i
    private int numberOfEpisodes(Role role) {

        //Collections.frequency gir tilbake antall forekomster av et element i en liste
        int occurrences = Collections.frequency(getCast(), role);
        return occurrences;
    }

    public void getCastWithNumberOfEpisodes() {
        for (Role role : getCastUnique())
            System.out.println(role.getActor() + " plays " + role + " in " + numberOfEpisodes(role) + " episodes");
    }

    public static ArrayList<TVSeries> getSortedList() {
        Collections.sort(tvSeriesList);
        return tvSeriesList;

    }

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
