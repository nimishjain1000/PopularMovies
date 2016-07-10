package com.example.nimish.popularmovies;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class FetchData extends AsyncTask<Void, Void, Movie[]> {

     //use api by BuildConfig.THE_MOVIE_DB_API_TOKEN)

    private Movie[] getMovieDataFromJson(String movieJsonStr)
            throws JSONException {

    final String resultArray="results";

        final String mOverView="overview";
        final String mTitle="title";
        final String mReleaseDate="release_date";
        final String mPosterPath="poster_path";



        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(resultArray);
        for(int i=0;i<20;i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            String moviedesc = movieObject.getString(mOverView);
            String movietitle = movieObject.getString(mTitle);
            String movierelaease = movieObject.getString(mReleaseDate);
            String moviePosterPath = movieObject.getString(mPosterPath);

            MainActivityFragment.movieDetails[i] = new Movie(moviePosterPath, moviedesc, movietitle, movierelaease);
        }
        return MainActivityFragment.movieDetails;

    }

    @Override
    protected Movie[] doInBackground(Void... params) {
        return new Movie[0];
    }
}
