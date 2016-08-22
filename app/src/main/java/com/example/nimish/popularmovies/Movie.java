package com.example.nimish.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class Movie implements Parcelable{
    private String title;
    private String posterPath;
    private String releaseDate;
    private float userRating;
    private String overView;
    private String backdropPath;


    public Movie(){}

    public float getUserRating() {
        return userRating;
    }

    public Movie(String title, String posterPath, String releaseDate, float userRating, String overView, String backdropPath) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.userRating = userRating;
        this.overView = overView;
        this.backdropPath = backdropPath;
    }

    public String  getPosterPath(){
        final String BASE_URL = "http://image.tmdb.org/t/p/w342/";
        return BASE_URL + posterPath;
    }

    public String getBackdropPath() {
        final String BASE_URL = "http://image.tmdb.org/t/p/w500/";
        return BASE_URL + backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
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

    protected Movie(Parcel in)
    {
        title=in.readString();
        posterPath=in.readString();
        releaseDate=in.readString();
        userRating = in.readFloat();
        overView = in.readString();
        backdropPath = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeFloat(userRating);
        dest.writeString(overView);
        dest.writeString(backdropPath);
    }



    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}

