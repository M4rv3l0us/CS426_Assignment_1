package com.example.myapplication;
import  java.util.List;
public class MovieInfo {
    String MovieTitle;
    String IMDB;
    Integer Runtime;
    String MovieFormat;
    String Description;
    List <String> Genres;

    public String getMovieTitle() {
        return MovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public String getIMDB() {
        return IMDB;
    }

    public void setIMDB(String IMDB) {
        this.IMDB = IMDB;
    }

    public Integer getRuntime() {
        return Runtime;
    }

    public void setRuntime(Integer runtime) {
        Runtime = runtime;
    }

    public String getMovieFormat() {
        return MovieFormat;
    }

    public void setMovieFormat(String movieFormat) {
        MovieFormat = movieFormat;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getGenres() {
        return Genres;
    }

    public void setGenres(List<String> genres) {
        Genres = genres;
    }

    public MovieInfo(String movieTitle, String IMDB, Integer runtime, String movieFormat, String description, List<String> genres) {
        MovieTitle = movieTitle;
        this.IMDB = IMDB;
        Runtime = runtime;
        MovieFormat = movieFormat;
        Description = description;
        Genres = genres;
    }

}
