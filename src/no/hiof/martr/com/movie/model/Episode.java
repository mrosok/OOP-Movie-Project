package no.hiof.martr.com.movie.model;

import java.time.LocalDate;

public class Episode extends Production implements Comparable<Episode> {
    private int episodeNumber;
    private int season;

    // Constructors

    public Episode(int episodeNumber, int season, String title, String description, LocalDate releaseDate, int runtime) {
        super(title, description, releaseDate, runtime);
        this.episodeNumber = episodeNumber;
        this.season = season;
    }

    public Episode(int episodeNumber, int season, String title, String description , LocalDate releaseDate) {
        super(title, description, releaseDate);
        this.episodeNumber = episodeNumber;
        this.season = season;
    }

    @Override
    public int compareTo(Episode o) {
        if(this.season < o.season)
             return -1;

        if(this.season == o.season) {
            if(this.episodeNumber < o.episodeNumber)
                return -1;
            else return 1;
        }
        return 1;
    }

    // Getters and Setters

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "S" + this.season + "E" + this.episodeNumber + ": " + super.getTitle();
    }
}
