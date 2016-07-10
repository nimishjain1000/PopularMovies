package com.example.nimish.popularmovies;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class Movie {
    private String posterPath;
    private String overview;
    private String title;
    private String releaseDate;

    public Movie(){}

    public Movie(String posterPath, String overview, String title, String releaseDate) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
