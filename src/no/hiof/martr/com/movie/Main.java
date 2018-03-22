package no.hiof.martr.com.movie;

import no.hiof.martr.com.movie.model.Episode;
import no.hiof.martr.com.movie.model.Movie;
import no.hiof.martr.com.movie.model.TVSeries;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        // testdata
       // MainJavaFX moviedata = new MainJavaFX();

        TVSeries itCrowd = new TVSeries("The IT Crowd",
                "The comedic adventures of a rag-tag group of technical support workers at a large corporation.",
                2006);

        TVSeries community = new TVSeries("Community",
                "A suspended lawyer is forced to enroll in a community college with an eclectic staff and student body.",
                2009);

        TVSeries seinfeld = new TVSeries("Seinfeld",
                "The continuing misadventures of neurotic New York stand-up comedian Jerry Seinfeld and his equally neurotic New York friends",
                1989);

        seinfeld.createEpisodes(2,10);

        //test sortering
        for (Movie movie : Movie.getSortedList())
            System.out.println(movie);

        System.out.println("\n*****************\n");

        for (TVSeries series : TVSeries.getSortedList())
            System.out.println(series);

        System.out.println("\n*****************\n");

        Collections.sort(seinfeld.getEpisodes());
        for (Episode episode : seinfeld.getEpisodes())
            System.out.println(episode);
    }
}
